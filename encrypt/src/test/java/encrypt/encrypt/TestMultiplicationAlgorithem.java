package encrypt.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class TestMultiplicationAlgorithem {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMultiplicationAction() {
		MultiplicationAlgorithemEncryptor test=mock(MultiplicationAlgorithemEncryptor.class, Mockito.CALLS_REAL_METHODS);

		test.setKey(10);
		byte[] arr={5,25};
		byte[] result={50,-6};
		assertArrayEquals(result,test.MultiplicationAction(arr));
	}

	@Test
	public void testMWO() {
		MultiplicationAlgorithemEncryptor test=mock(MultiplicationAlgorithemEncryptor.class, Mockito.CALLS_REAL_METHODS);
		assertEquals(20,test.MWO((byte)10, 2));
		assertEquals(-6,test.MWO((byte)10, 25));
		}

}
