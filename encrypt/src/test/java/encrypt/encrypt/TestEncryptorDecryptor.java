package encrypt.encrypt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
public class TestEncryptorDecryptor {
	@Rule
	 public TemporaryFolder folder = new TemporaryFolder();
	@SuppressWarnings("unused")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReadFromFile() throws IOException {
		SimpleAlgorithem test=mock(SimpleAlgorithem.class, Mockito.CALLS_REAL_METHODS);
		byte[] data={21,35,47,25,-26,-46};
		File testFile=folder.newFile("test.txt");
		test.setFilePath(testFile.toPath());
		test.writeToFile(data,testFile.toString());
		assertArrayEquals(data,test.readFromFile());
		folder.delete();
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testWriteToFile() throws IOException {
		SimpleAlgorithem test=mock(SimpleAlgorithem.class, Mockito.CALLS_REAL_METHODS);
		byte[] data={21,35,47,25,-26,-46};
		File testFile=folder.newFile("test.txt");
		test.writeToFile(data ,testFile.toString());
		assertTrue(testFile.isFile());
		assertArrayEquals(data,Files.readAllBytes(Paths.get(testFile.toString())));
		folder.delete();

	}


}
