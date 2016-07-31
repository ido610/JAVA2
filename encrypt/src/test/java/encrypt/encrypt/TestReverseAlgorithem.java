package encrypt.encrypt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestReverseAlgorithem {

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
	public void testAction() throws IOException {
		ReverseAlgorithem x=new ReverseAlgorithem(System.getProperty("user.dir")+"/src/test/java/test.txt",1);
		x.action();
		ReverseAlgorithem y=new ReverseAlgorithem(System.getProperty("user.dir")+"/src/test/java/test_decrypted.txt",2);
		y.action();
		assertEquals("The files differ!", 
			    FileUtils.readFileToString(new File("src/test/java/test.txt"), "utf-8"), 
			    FileUtils.readFileToString(new File("src/test/java/test_decrypted.txt"), "utf-8"));	}	

}
