package it.albertus.cyclesmod.common.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import it.albertus.cyclesmod.common.engine.InvalidPropertyException;
import it.albertus.cyclesmod.common.resources.CommonMessages;
import it.albertus.cyclesmod.common.resources.Messages;
import it.albertus.util.ByteUtils;
import lombok.NonNull;

public class Gearbox implements ByteList {

	public static final int LENGTH = 20;
	public static final int MIN_VALUE = 0;

	public static final String PREFIX = "gearbox";

	private static final Messages messages = CommonMessages.INSTANCE;

	/**
	 * I valori sono a 16 bit, ma di fatto vengono considerati solo 8 bit (si
	 * procede per multipli di 256).
	 */
	public static final int MAX_VALUE = 65535;

	/** 22-41 (N, 1, 2, 3, 4, 5, 6, 7, 8, 9) */
	private final int[] ratios;

	public Gearbox(@NonNull final int[] ratios) {
		if (ratios.length > LENGTH / 2) {
			throw new IllegalArgumentException(messages.get("common.err.gearbox", LENGTH / 2, ratios.length));
		}
		this.ratios = Arrays.copyOf(ratios, LENGTH / 2);
	}

	@Override
	public List<Byte> toByteList() {
		final List<Byte> byteList = new ArrayList<>(LENGTH);
		for (final int ratio : ratios) {
			byteList.addAll(ByteUtils.toByteList(ratio));
		}
		return byteList;
	}

	public static int parse(final String key, @NonNull final String value, final int radix) {
		final long newValue = Long.parseLong(value.trim(), radix);
		if (newValue < MIN_VALUE || newValue > MAX_VALUE) {
			throw new InvalidPropertyException(messages.get("common.err.illegal.value", Integer.toString(MIN_VALUE, radix).toUpperCase(Locale.ROOT), Integer.toString(MAX_VALUE, radix).toUpperCase(Locale.ROOT), key, Long.toString(newValue, radix).toUpperCase(Locale.ROOT)));
		}
		return (int) newValue;
	}

	public int[] getRatios() {
		return ratios;
	}

}
