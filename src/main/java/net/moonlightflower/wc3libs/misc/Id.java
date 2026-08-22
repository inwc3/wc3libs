package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.War3String;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class Id extends War3String {
	/**
	 * The form an id is stored in: the same four bytes with any padding NULs
	 * removed.
	 * <p>
	 * An id occupies four bytes on disk and an unset one is four NULs, so the
	 * same id arrives spelled two ways depending on where it came from -- and
	 * the binary readers already trim, while a literal in this library's own
	 * code does not. Normalising on construction is what lets
	 * {@code Id.valueOf("\0\0\0\0")} and an id read from four zero bytes be the
	 * same id. They used to be, but only because both hashed to zero: equality
	 * compared hashes, so it held for the empty id by luck and failed for any
	 * other pair that collided.
	 *
	 * @param val an id's characters, NUL-padded or not.
	 * @return the canonical spelling; empty for an unset id.
	 */
	@Nonnull
	public static String canonical(@Nullable String val) {
		if (val == null) return "";

		int start = 0;
		int end = val.length();

		while (start < end && val.charAt(start) == '\0') start++;
		while (end > start && val.charAt(end - 1) == '\0') end--;

		return (start == 0 && end == val.length()) ? val : val.substring(start, end);
	}

	/**
	 * @return whether this is the unset id, which on disk is four NUL bytes.
	 */
	public boolean isEmpty() {
		return getVal().isEmpty();
	}

	/**
	 * Hashing and equality are the inherited ones, over the id's characters.
	 * <p>
	 * A four-character id used to hash to the integer it encodes, and equality
	 * compared those hashes. That was fine for ids of exactly four printable
	 * characters and wrong everywhere else: shorter ids fell back to the string
	 * hash and so compared equal on a collision, and any id holding a byte
	 * outside ASCII -- which happens the moment a reader speculatively reads
	 * four bytes to see whether they spell a known token -- could not be
	 * encoded at all.
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;

		if (other instanceof Id id) return equals(id);

		return super.equals(other);
	}

	public boolean equals(Id other) {
		return Objects.equals(getVal(), other.getVal());
	}

	@Override
	public Object toSLKVal() {
		return getVal();
	}
	
	@Override
	public Object toTXTVal() {
		return getVal();
	}
	
	protected Id(String val) {
		super(canonical(val));
	}
	
	public static Id valueOf(String idString) {
		return new Id(idString);
	}

	@Override
	public Id decode(Object val) {
		return Id.valueOf(val.toString());
	}
	
	public Id lower() {
		return Id.valueOf(getVal().toLowerCase());
	}
}