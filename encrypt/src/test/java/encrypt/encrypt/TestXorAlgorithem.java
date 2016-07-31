package encrypt.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class TestXorAlgorithem {

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
	public void testXorAction() {
		XorAlgorithem test=mock(XorAlgorithem.class, Mockito.CALLS_REAL_METHODS);
		//XorAlgorithemDecryptor test =new XorAlgorithemDecryptor("x");
        test.setKey(10);
        byte[] data={2,5,7,10};
        byte[] result={8,15,13,0};
        data=test.xorAction(data);
        assertArrayEquals("Should be equals",data,result);
	}

}
