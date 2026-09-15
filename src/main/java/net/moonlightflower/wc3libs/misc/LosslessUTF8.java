package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

/**
 * UTF-8 codec which preserves malformed input bytes for a later write.
 *
 * Warcraft III files from older locale-specific editors can contain bytes
 * which are not valid UTF-8. Isolated low surrogates U+DC00..U+DCFF are used
 * as internal byte markers; valid UTF-8 can never decode to those values.
 */
public final class LosslessUTF8 {
    private static final char RAW_BYTE_BASE = '\uDC00';
    private static final char RAW_BYTE_END = '\uDCFF';

    private LosslessUTF8() {
    }

    @Nonnull
    public static String decode(@Nonnull byte[] bytes) {
        if (bytes.length == 0) return "";

        ByteBuffer input = ByteBuffer.wrap(bytes);
        CharBuffer chars = CharBuffer.allocate(bytes.length);
        StringBuilder result = new StringBuilder(bytes.length);
        java.nio.charset.CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder()
            .onMalformedInput(CodingErrorAction.REPORT)
            .onUnmappableCharacter(CodingErrorAction.REPORT);

        while (input.hasRemaining()) {
            CoderResult coderResult = decoder.decode(input, chars, true);
            chars.flip();
            result.append(chars);
            chars.clear();

            if (coderResult.isUnderflow()) break;
            if (coderResult.isOverflow()) continue;

            int malformedLength;
            try {
                malformedLength = coderResult.length();
            } catch (UnsupportedOperationException e) {
                throw new IllegalStateException(e);
            }

            for (int i = 0; i < malformedLength; i++) {
                result.append((char) (RAW_BYTE_BASE + (input.get() & 0xFF)));
            }
            decoder.reset();
        }

        return result.toString();
    }

    @Nonnull
    public static byte[] encode(@Nonnull String value) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int textStart = 0;

        for (int i = 0; i < value.length(); i++) {
            char current = value.charAt(i);
            boolean pairedLowSurrogate = i > 0 && Character.isHighSurrogate(value.charAt(i - 1));
            boolean rawByte = current >= RAW_BYTE_BASE && current <= RAW_BYTE_END && !pairedLowSurrogate;
            if (!rawByte) continue;

            writeUtf8(output, value.substring(textStart, i));
            output.write(current - RAW_BYTE_BASE);
            textStart = i + 1;
        }

        writeUtf8(output, value.substring(textStart));
        return output.toByteArray();
    }

    private static void writeUtf8(@Nonnull ByteArrayOutputStream output, @Nonnull String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        output.write(bytes, 0, bytes.length);
    }
}
