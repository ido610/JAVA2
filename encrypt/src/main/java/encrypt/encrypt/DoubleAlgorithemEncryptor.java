/**
 * 
 */
package encrypt.encrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author עידו
 *
 */
public class DoubleAlgorithemEncryptor extends DoubleAlgorithem {

	/**
	 * @param path
	 */
	public DoubleAlgorithemEncryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public DoubleAlgorithemEncryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see encrypt.encrypt.DoubleAlgorithem#initAlgo(int, int)
	 * Init algo's according to user choices
	 */
	@Override
	public void initAlgo(int num, int choice) {
			switch(choice){
				case 1:{
					if(num==1){
						a= new CeaserEncryptor(this.getPath());
					}else{
						b= new CeaserEncryptor(this.getPath());
					}
					break;
				}
				case 2:{
					if(num==1){
						a= new XorAlgorithemEncryptor(this.getPath());
					}else{
						b= new XorAlgorithemEncryptor(this.getPath());

					}
					break;
				}
				case 3:{
					if(num==1){
						a= new MultiplicationAlgorithemEncryptor(this.getPath());
					}else{
						b= new MultiplicationAlgorithemEncryptor(this.getPath());

					}
					break;
				}
			}
			
	}



	public void action() {
		chooseEncryptorDecryptorAlgo();
		a.action();//First algo encryption
		a.keyToBin("key1.bin");//Write key for first algo
		b.setFilePath(a.getFilePathAfter());
		b.action();//Second algo encryption
		b.keyToBin("key2.bin");//Write key for second algo
	    try {
			Files.delete(a.getFilePathAfter());//Delete temporary file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
