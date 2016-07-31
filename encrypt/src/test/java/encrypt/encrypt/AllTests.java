package encrypt.encrypt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestEncryptorDecryptor.class, TestCeaserAlgorithem.class,
		TestCeaserDecryptor.class, TestCeaserEncryptor.class,
		TestMultiplicationAlgorithem.class,
		TestMultiplicationAlgorithemEncryptor.class,TestMultiplicationAlgorithemDecryptor.class, TestXorAlgorithem.class,
		TestXorAlgorithemDecryptor.class, TestXorAlgorithemEncryptor.class,TestHelperProg.class,
		TestSplitAlgorithemDecryptor.class,TestSplitAlgorithemEncryptor.class
		,TestDoubleAlgorithemEncryptor.class,TestDoubleAlgorithemDecryptor.class})
public class AllTests {

}
