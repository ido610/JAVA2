/**
 * 
 */
package encrypt.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author עידו
 *
 */
public class DoubleAlgorithemDecryptor extends DoubleAlgorithem {

	/**
	 * @param path
	 */
	public DoubleAlgorithemDecryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public DoubleAlgorithemDecryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see encrypt.encrypt.DoubleAlgorithem#initAlgo(int, int)
	 */
	@Override
	public void initAlgo(int num, int choice) {
		switch(choice){
		case 1:{
			if(num==1){
				a= new CeaserDecryptor(this.getPath());
			}else{
				b= new CeaserDecryptor(this.getPath());
			}
			break;
		}
		case 2:{
			if(num==1){
				a= new XorAlgorithemDecryptor(this.getPath());
			}else{
				b= new XorAlgorithemDecryptor(this.getPath());

			}
			break;
		}
		case 3:{
			if(num==1){
				a= new MultiplicationAlgorithemDecryptor(this.getPath());
			}else{
				b= new MultiplicationAlgorithemDecryptor(this.getPath());

			}
			break;
		}
	}
	}
	protected void chooseEncryptorDecryptorAlgo(){
		System.out.println("Choose same order as encryption");
		super.chooseEncryptorDecryptorAlgo();
	}
	public void action() {
		// TODO Auto-generated method stub
		chooseEncryptorDecryptorAlgo();
		b.action();//Second algo decrypt
		a.setFilePath(b.getFilePathAfter());
		a.action();//First algo decrypt
	    try {
			Files.delete(b.getFilePathAfter());//Temp file delete
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File before=a.getFilePathAfter().toFile();
		String[] tokens = a.getFilePathAfter().toString().split("\\.(?=[^\\.]+$)");
		String path=tokens[0]+"."+tokens[1].split("_")[0];
		File after=new File(path);
		before.renameTo(after);
		System.out.println("Final path for decryption is:"+path);
	}




}
