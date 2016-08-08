/**
 * 
 */
package encrypt.encrypt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author עידו
 *
 */
public class TestMultiplicationAlgorithemDecryptor {
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("-------Start testing MultiplicationAlgorithemDecryptor"+"-----");

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
	 * Test method for {@link encrypt.encrypt.MultiplicationAlgorithemDecryptor#action()}.
	 * @throws IOException 
	 */
	@Test
	public void testAction() throws IOException {
		
		File createdFile= folder.newFile("myfile.txt");
	    Files.write(Paths.get(createdFile.getPath()),new String("Hello World").getBytes());
	    MultiplicationAlgorithemEncryptor x=new MultiplicationAlgorithemEncryptor(createdFile.getPath());
		x.action();
		MultiplicationAlgorithemDecryptor y=new MultiplicationAlgorithemDecryptor(createdFile.getPath()+".encrypted");
		y.action();
		assertEquals("The files differ!", 
			    FileUtils.readFileToString(createdFile, "utf-8"), 
			    FileUtils.readFileToString(new File(folder.getRoot()+"/myfile_decrypted.txt"), "utf-8"));	
		folder.delete();
	}
}
