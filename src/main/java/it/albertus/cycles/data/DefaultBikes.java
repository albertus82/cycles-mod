package it.albertus.cycles.data;

import it.albertus.cycles.model.BikesInf;
import it.albertus.cycles.resources.Resources;

import java.io.ByteArrayInputStream;
import java.io.StreamCorruptedException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class DefaultBikes {

	private static final byte[] DEFAULT = { 0x06, 0x00, (byte) 0xA8, 0x2F, (byte) 0xB0, 0x36, 0x72, 0x01, (byte) 0xEE,
			0x02, (byte) 0xEE, 0x02, (byte) 0xEE, 0x02, (byte) 0xEE, 0x02, 0x62, 0x02, 0x62, 0x02, 0x1C, 0x25, 0x00,
			0x00, 0x00, (byte) 0xB0, 0x00, (byte) 0x99, 0x00, (byte) 0x85, 0x00, 0x73, 0x00, 0x65, 0x00, 0x59, 0x19,
			0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19,
			0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19,
			0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x19,
			0x19, 0x19, 0x19, 0x19, 0x19, 0x19, 0x1C, 0x1C, 0x1C, 0x1C, 0x1C, 0x1C, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F,
			0x1F, 0x21, 0x23, 0x23, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x24, 0x21,
			0x21, 0x21, 0x21, 0x21, 0x21, 0x21, 0x21, 0x21, 0x19, 0x0F, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x06, 0x00, (byte) 0xA8, 0x2F, (byte) 0xB0, 0x36, 0x72, 0x01, (byte) 0xEE, 0x02,
			(byte) 0xEE, 0x02, (byte) 0xEE, 0x02, (byte) 0xEE, 0x02, 0x76, 0x02, 0x76, 0x02, 0x1C, 0x25, 0x00, 0x00,
			0x00, (byte) 0xA0, 0x00, (byte) 0x88, 0x00, 0x73, 0x00, 0x61, 0x00, 0x56, 0x00, 0x4E, 0x25, 0x25, 0x25,
			0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25,
			0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25,
			0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25,
			0x25, 0x25, 0x25, 0x25, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2D, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F,
			0x32, 0x34, 0x37, 0x37, 0x37, 0x37, 0x37, 0x37, 0x37, 0x37, 0x37, 0x37, 0x37, 0x32, 0x32, 0x32, 0x32, 0x32,
			0x32, 0x32, 0x32, 0x2F, 0x2D, 0x28, 0x23, 0x1C, 0x14, 0x0F, 0x0A, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x06, 0x00, 0x38, 0x31, (byte) 0xB0, 0x36, 0x72, 0x01, 0x0C, 0x03, 0x0C, 0x03, (byte) 0xEE, 0x02,
			(byte) 0xEE, 0x02, (byte) 0x8A, 0x02, (byte) 0x8A, 0x02, 0x22, 0x24, 0x00, 0x00, 0x00, (byte) 0x92, 0x00,
			0x7C, 0x00, 0x69, 0x00, 0x59, 0x00, 0x4E, 0x00, 0x44, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F,
			0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F,
			0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F, 0x2F,
			0x2F, 0x2F, 0x2F, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x4B, 0x4B,
			0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B,
			0x50, 0x50, 0x50, 0x50, 0x50, 0x50, 0x50, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B, 0x4B,
			0x46, 0x41, 0x37, 0x2D, 0x23, 0x14, 0x0A, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00 };

	public DefaultBikes() throws StreamCorruptedException {
		final Checksum crc = new CRC32();
		crc.update(DEFAULT, 0, DEFAULT.length);
		if (crc.getValue() != BikesInf.FILE_CRC) {
			throw new StreamCorruptedException(Resources.get("err.original.file.corrupted.crc", BikesInf.FILE_NAME, String.format("%08X", BikesInf.FILE_CRC), String.format("%08X", crc.getValue())));
		}
		if (DEFAULT.length != BikesInf.FILE_SIZE) {
			throw new StreamCorruptedException(Resources.get("err.original.file.corrupted.size", BikesInf.FILE_NAME, BikesInf.FILE_SIZE, DEFAULT.length));
		}
	}

	public ByteArrayInputStream getInputStream() {
		return new ByteArrayInputStream(DEFAULT);
	}

}