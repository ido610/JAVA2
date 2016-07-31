package encrypt.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class TestCeaserAlgorithem {

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
	public void testByteAfterAction() {
		CeaserAlgorithem test=mock(CeaserAlgorithem.class, Mockito.CALLS_REAL_METHODS);
        byte data = test.byteAfterAction(10);
        assertEquals("Should be 10",(byte)10,data);
        data = test.byteAfterAction(140);
        assertEquals("Should be -116",(byte)-116,data);

	}

}
