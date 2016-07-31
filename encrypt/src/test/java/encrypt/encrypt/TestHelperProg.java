/**
 * 
 */
package encrypt.encrypt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author עידו
 *
 */
public class TestHelperProg {
	 public TemporaryFolder folder = new TemporaryFolder();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link encrypt.encrypt.HelperProg#getActionrUserChoice()}.
	 */
	@Test
	public void testGetActionrUserChoice() {
		HelperProg x=new HelperProg();
		int y=x.getActionrUserChoice();
		assertTrue("Value should be between 1-2",y>=1 && y<=2);		}

	/**
	 * Test method for {@link encrypt.encrypt.HelperProg#getAlgoUserChoice()}.
	 */
	@Test
	public void testGetAlgoUserChoice() {
		HelperProg x=new HelperProg();
		int y=x.getAlgoUserChoice();
		assertTrue("Value should be between 1-6",y>=1 && y<=6);	
		
	}

	/**
	 * Test method for {@link encrypt.encrypt.HelperProg#getFilePath()}.
	 * @throws IOException 
	 */
	@Test
	public void testGetFilePath() throws IOException {
		HelperProg x=new HelperProg();
        File y=new File(x.getFilePath());
        assertTrue("File should be exist",y.exists());
        assertFalse("Supposed to be file",y.isDirectory());

	}

	/**
	 * Test method for {@link encrypt.encrypt.HelperProg#createAnObject(int, int, java.lang.String)}.
	 */
	@Test
	public void testCreateAnObject() {
		HelperProg x=new HelperProg();
        assertEquals("Should be Ceaser encryptor class",x.createAnObject(1,1,"x").getClass(),CeaserEncryptor.class);
        assertEquals("Should be Xor decryptor class",x.createAnObject(2,2,"x").getClass(),XorAlgorithemDecryptor.class);
        assertNotSame("Should be Xor decryptor class",x.createAnObject(2,2,"x").getClass(),XorAlgorithemEncryptor.class);

        
        
	}

}
