package net.moonlightflower.wc3libs.misc.exeversion;

import javax.annotation.Nonnull;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * Extracts the FileVersion from a Windows PE executable's {@code VS_VERSIONINFO} resource, without any external
 * dependency.
 * <p>
 * The version lives in the {@code .rsrc} section as a {@code VS_FIXEDFILEINFO} structure, which begins with the
 * canonical signature {@code 0xFEEF04BD} and is followed by {@code dwFileVersionMS}/{@code dwFileVersionLS}. We
 * locate {@code .rsrc} via the PE/COFF headers and scan it for that signature, then decode the four version words.
 * This works for both PE32 and PE32+ images (32- and 64-bit), which covers every Warcraft III client executable,
 * and avoids the unmaintained PE-parser library that previously failed (NPE) on some game binaries.
 */
public class ExeVersionPe implements ExeVersion {
    private static final int VS_FIXED_FILE_INFO_SIGNATURE = 0xFEEF04BD;

    /**
     * @param executablePath Path to the executable file
     * @return Dotted FileVersion string ({@code major.minor.build.revision}).
     * @throws VersionExtractionException if the file is not a readable PE or has no version resource.
     */
    @Nonnull
    public static String getVersion(String executablePath) throws VersionExtractionException {
        Objects.requireNonNull(executablePath);
        try (RandomAccessFile file = new RandomAccessFile(executablePath, "r");
             FileChannel channel = file.getChannel()) {
            long size = channel.size();
            if (size <= 0 || size > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("unsupported file size: " + size);
            }
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            buffer.order(ByteOrder.LITTLE_ENDIAN);

            int sigOffset = findFixedFileInfo(buffer, (int) size);
            if (sigOffset < 0) {
                throw new IllegalStateException("no VS_VERSIONINFO (VS_FIXEDFILEINFO) resource found");
            }

            int fileVersionMs = buffer.getInt(sigOffset + 8);
            int fileVersionLs = buffer.getInt(sigOffset + 12);
            int major = (fileVersionMs >>> 16) & 0xFFFF;
            int minor = fileVersionMs & 0xFFFF;
            int build = (fileVersionLs >>> 16) & 0xFFFF;
            int revision = fileVersionLs & 0xFFFF;

            return major + "." + minor + "." + build + "." + revision;
        } catch (Exception e) {
            throw new VersionExtractionException(executablePath, VersionExtractionException.getMethodPeHeader(), e);
        }
    }

    /** Returns the file offset of the VS_FIXEDFILEINFO signature, preferring the {@code .rsrc} section, or -1. */
    private static int findFixedFileInfo(MappedByteBuffer buffer, int size) {
        long rsrc = locateResourceSection(buffer, size);
        if (rsrc >= 0) {
            int start = (int) (rsrc >>> 32);
            int length = (int) (rsrc & 0xFFFFFFFFL);
            int found = scanForSignature(buffer, start, Math.min(size, start + length));
            if (found >= 0) {
                return found;
            }
        }
        // Fallback: a well-formed PE always carries the resource in .rsrc, but scan the whole image as a safety net.
        return scanForSignature(buffer, 0, size);
    }

    /**
     * Locates the {@code .rsrc} section's raw data via the PE/COFF headers.
     *
     * @return {@code (fileOffset << 32) | length} packed into a long, or -1 if not found. The packing avoids an
     * allocation in the common path.
     */
    private static long locateResourceSection(MappedByteBuffer buffer, int size) {
        if (size < 0x40 || buffer.get(0) != 'M' || buffer.get(1) != 'Z') {
            return -1;
        }
        int peOffset = buffer.getInt(0x3C);
        if (peOffset <= 0 || (long) peOffset + 24 > size) {
            return -1;
        }
        if (buffer.get(peOffset) != 'P' || buffer.get(peOffset + 1) != 'E'
            || buffer.get(peOffset + 2) != 0 || buffer.get(peOffset + 3) != 0) {
            return -1;
        }
        int numberOfSections = buffer.getShort(peOffset + 6) & 0xFFFF;
        int sizeOfOptionalHeader = buffer.getShort(peOffset + 20) & 0xFFFF;
        // Section table follows the 4-byte signature + 20-byte COFF header + optional header. Using
        // SizeOfOptionalHeader keeps this independent of PE32 vs PE32+.
        int sectionTable = peOffset + 24 + sizeOfOptionalHeader;
        for (int i = 0; i < numberOfSections; i++) {
            int entry = sectionTable + i * 40;
            if ((long) entry + 40 > size) {
                break;
            }
            if (isRsrcName(buffer, entry)) {
                int sizeOfRawData = buffer.getInt(entry + 16);
                int pointerToRawData = buffer.getInt(entry + 20);
                if (pointerToRawData >= 0 && sizeOfRawData > 0 && (long) pointerToRawData + sizeOfRawData <= size) {
                    return ((long) pointerToRawData << 32) | (sizeOfRawData & 0xFFFFFFFFL);
                }
            }
        }
        return -1;
    }

    /** Matches the 8-byte section name {@code ".rsrc\0\0\0"}. */
    private static boolean isRsrcName(MappedByteBuffer buffer, int entry) {
        return buffer.get(entry) == '.' && buffer.get(entry + 1) == 'r' && buffer.get(entry + 2) == 's'
            && buffer.get(entry + 3) == 'r' && buffer.get(entry + 4) == 'c' && buffer.get(entry + 5) == 0;
    }

    private static int scanForSignature(MappedByteBuffer buffer, int from, int to) {
        for (int pos = Math.max(from, 0); pos + 4 <= to; pos++) {
            if (buffer.getInt(pos) == VS_FIXED_FILE_INFO_SIGNATURE) {
                return pos;
            }
        }
        return -1;
    }
}
