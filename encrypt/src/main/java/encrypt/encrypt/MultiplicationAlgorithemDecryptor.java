/**
 * 
 */
package encrypt.encrypt;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import lombok.Data;

/**
 * @author עידו
 *
 */
public @Data class MultiplicationAlgorithemDecryptor extends MultiplicationAlgorithem {
	   static Logger log = Logger.getLogger(MultiplicationAlgorithemDecryptor.class.getName());

	/**
	 * @param path
	 */
	public MultiplicationAlgorithemDecryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public MultiplicationAlgorithemDecryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
				starter();
				//safePrintln("Start Multiplication decryption for file: "+getFilePath());
			     log.info("Start Multiplication decryption for file: "+getFilePath());

				byte[] data=decryptData();//Get file bytes array
				long startTime = System.currentTimeMillis();
				setKey();
				data=MultiplicationAction(data);//Decrypt all bytes
					String[] tokens = getFilePath().toString().split("\\.(?=[^\\.]+$)");
				tokens=tokens[0].split("\\.(?=[^\\.]+$)");
				String path=tokens[0]+"_decrypted."+tokens[1];
				writeToFile(data,path);//Write decrypted content in file
				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				//safePrintln("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
			     log.info("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");

				end();
	}
	/*Find the key by finding MWO between user input and byte that will lead to result 1*/
	public void setKey() {
		for(byte x=-127;x<=128;x++){
			if(MWO(x,getKey())==1){
				setKey(x);
				break;
			}
		}
		safePrintln("Decryption key is:"+getKey());
		}

	public void run() {
		action();
		
	}
}
