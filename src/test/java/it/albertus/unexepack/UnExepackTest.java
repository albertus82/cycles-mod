package it.albertus.unexepack;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.albertus.BaseTest;
import lombok.extern.java.Log;

@Log
class UnExepackTest extends BaseTest {

	private static final Map<String, String> digests = new HashMap<>();

	private static final byte[] MZ = new byte[] { 0x4D, 0x5A, (byte) 0xB5, 0x00, (byte) 0x99, 0x00, 0x00, 0x00, 0x20, 0x00, (byte) 0xA9, 0x05, (byte) 0xA9, 0x05, (byte) 0xC5, 0x17, (byte) 0x80, 0x00, 0x00, 0x00, 0x12, 0x00, (byte) 0xD2, 0x12, 0x1E, 0x00, 0x00, 0x00 };
	private static final byte[] RB = new byte[] { (byte) 0x94, (byte) 0xBB, 0x00, 0x00, 0x00, 0x00, (byte) 0x95, 0x01, 0x00, 0x08, 0x14, 0x18, (byte) 0xAB, 0x17, 0x01, 0x00, 0x52, 0x42 };

	@BeforeAll
	static void beforeAll() {
		digests.put("A.EXE", "592744c31541044c673e4647cc75f853abbc508d7bc56dcbcae9578e07f2f39c");
		digests.put("B.EXE", "d5d3127e36ec942fd8f722714f6604f28a2fed5c305e5c91648d16285396cf2b");
		digests.put("C.EXE", "0a02d7e93da2b92914142daeea344c4b8d8adfce6dfae44118c46b65024ceaef");
		digests.put("D.EXE", "5fd858d7e7a4d10955d86496f7b19b1adebcb32eb0fb54f7733893ddca20a2d8");
		digests.put("E.EXE", "9402a6f4ca05cdbb26d612f3ab79b72bce2899da96259d1d042ef164d6f159a6");
		digests.put("F.EXE", "c82518469a2c2f06a24f8fb37a59459ae1ce19186dd71095e3ed5b3695d0f6ca");
		digests.put("G.EXE", "c91aa00c7de39088a2b5b92e89850ea8aaa3590eda2f1d729a5425c029673fcb");
		digests.put("H.EXE", "9402a6f4ca05cdbb26d612f3ab79b72bce2899da96259d1d042ef164d6f159a6");
		digests.put("I.EXE", "e9df83a3a7c4c47e544b1c3023986eb99be330ed779a3ede8ac61cdcfb22d046");
		digests.put("J.EXE", "ceb273d445168ca60dd5ac458f7207fe03aa21986521903a08c177346ea8a0f2");
		digests.put("K.EXE", "9b8dc9e4208ef1b4a2af3b516c23a64007f55e9b24ab4fd019f68d454d380c8a");
		digests.put("L.EXE", "f5923fa58a07523a8ca850684b6c96737ac9cde9e938f16f1d48105bc9e8267b");
		digests.put("M.EXE", "8f397594e14eaf2b3e6174c4c747366dde1ef864a82d292c08225af4452537eb");
	}

	@Test
	void testHeaders() throws InvalidDosHeaderException {
		Assertions.assertDoesNotThrow(() -> new DosHeader(MZ));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new DosHeader(new byte[27]));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new DosHeader(new byte[29]));

		DosHeader dh = new DosHeader(MZ);
		Assertions.assertArrayEquals(MZ, dh.toByteArray());

		Assertions.assertDoesNotThrow(() -> new ExepackHeader(RB));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new ExepackHeader(new byte[17]));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new ExepackHeader(new byte[19]));
	}

	@Test
	void testUnpack() throws IOException, InvalidHeaderException {
		final String propertyName = "testSecret";
		final String secret = System.getProperty(propertyName);
		if (secret == null) {
			log.log(Level.WARNING, "Missing system property ''{0}'', skipping unpacking test.", propertyName);
		}
		Assumptions.assumeTrue(secret != null);
		final Path path = Paths.get(projectProperties.getProperty("project.build.testSourceDirectory"), "..", "resources", "exepacked.7z");
		final SevenZFile sevenZFile = new SevenZFile(path.toFile(), secret.toCharArray());
		SevenZArchiveEntry entry;
		while ((entry = sevenZFile.getNextEntry()) != null) {
			log.log(Level.INFO, "{0}", entry.getName());
			final byte[] buf = new byte[(int) entry.getSize()];
			Assertions.assertEquals(entry.getSize(), sevenZFile.read(buf));
			Assertions.assertEquals(-1, sevenZFile.read());
			Assertions.assertEquals(digests.get(entry.getName()), DigestUtils.sha256Hex(UnExepack.unpack(buf)));
		}
	}

	@Test
	void testDecodeExeLen() {
		Assertions.assertEquals(0, UnExepack.decodeExeLen(0, 0));
		Assertions.assertEquals(1, UnExepack.decodeExeLen(1, 1));
		Assertions.assertEquals(511, UnExepack.decodeExeLen(511, 1));
		Assertions.assertEquals(512, UnExepack.decodeExeLen(0, 1));
		Assertions.assertEquals(513, UnExepack.decodeExeLen(1, 2));
		Assertions.assertEquals(0xFFFF * 512 - 1, UnExepack.decodeExeLen(511, 0xFFFF));
		Assertions.assertEquals(0xFFFF * 512, UnExepack.decodeExeLen(0, 0xFFFF));

		// When e_cp == 0, e_cblp must be 0, otherwise it would encode a negative length.
		Assertions.assertEquals(-1, UnExepack.decodeExeLen(1, 0));
		Assertions.assertEquals(-1, UnExepack.decodeExeLen(511, 0));
		// e_cblp must be <= 511.
		Assertions.assertEquals(-1, UnExepack.decodeExeLen(512, 1));
	}

}
