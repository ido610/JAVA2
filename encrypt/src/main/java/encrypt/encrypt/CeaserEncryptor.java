package encrypt.encrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;






import java.util.logging.Logger;

import lombok.Data;

/**
 * @author עידו
 *
 */
public @Data class CeaserEncryptor extends CeaserAlgorithem {
	   static Logger log = Logger.getLogger(CeaserEncryptor.class.getName());

	/**
	 * @param path
	 */
	CeaserEncryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	CeaserEncryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	public CeaserEncryptor(String path, int i) {
		// TODO Auto-generated constructor stub
		super(path,i);
	}
	@Override
	public void action() {
		starter();
		//safePrintln("Start ceaser encryption for file: "+getFilePath());
	     log.info("Start ceaser encryption for file "+getFilePath());

		long startTime = System.currentTimeMillis();
		byte[] data;
		data=encryptData();	//Get file byte array
		
		if(this.getMultipleFiles()!=1){
			keyToBin(this.getFilePath().getParent()+"/key.bin");//Write key to file
		}
		for(int i=0;i<data.length;i++){
			int b=data[i]+getKey();
			data[i]=byteAfterAction(b);//Fix overflow

		}
		String path=getFilePath().toString()+".encrypted";
		if(this.getMultipleFiles()==1){
			path=getFilePath().getParent().toString()+"/encrypted-decrypted/"+
					getFilePath().getFileName().toString()+".encrypted";
		}
		writeToFile(data,path);//Write encrypted bytes to path
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		//safePrintln("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
	     log.info("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
		end();
	}
	public void run() {
		action();
		
	}



}
