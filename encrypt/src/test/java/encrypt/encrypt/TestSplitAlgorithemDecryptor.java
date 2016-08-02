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

public class TestSplitAlgorithemDecryptor {
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();
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
		File createdFile= folder.newFile("myfile.txt");
	    Files.write(Paths.get(createdFile.getPath()),new String("Hello World").getBytes());
	    SplitAlgorithemEncryptor x=new SplitAlgorithemEncryptor(createdFile.getPath());
		x.action();
		SplitAlgorithemDecryptor y=new SplitAlgorithemDecryptor(createdFile.getPath()+".encrypted");
		y.action();
		assertEquals("The files differ!", 
			    FileUtils.readFileToString(createdFile, "utf-8"), 
			    FileUtils.readFileToString(new File(folder.getRoot()+"/myfile_decrypted.txt"), "utf-8"));	
		folder.delete();}

}
