package encrypt.encrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import lombok.Data;

/**
 * @author עידו
 *Class represent Ceaser Algorithem Decryptor
 */
public @Data class CeaserDecryptor extends CeaserAlgorithem {
	   static Logger log = Logger.getLogger(CeaserDecryptor.class.getName());
   
	/**
	 * @param path
	 */
	CeaserDecryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	CeaserDecryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	public CeaserDecryptor(String path, int i) {
		super(path,i);
	}
	@Override
	public void action() {
		starter();
		//safePrintln("Start ceaser decryption for file "+getFilePath());
	      log.info("Start ceaser decryption for file "+getFilePath());

		byte[] data;
		data=decryptData();
		
		this.setStartTime(System.currentTimeMillis());
		for(int i=0;i<data.length;i++){//For each byte in file
			int bit=data[i]-getKey();
			data[i]=byteAfterAction(bit);//Fix byte value with overflow

		}
		String[] tokens = getFilePath().toString().split("\\.(?=[^\\.]+$)");
		tokens=tokens[0].split("\\.(?=[^\\.]+$)");
		String path=tokens[0]+"_decrypted."+tokens[1];//File name after action
		if(this.getMultipleFiles()==1){
			path=Paths.get(path).getParent().toString()+"/encrypted-decrypted/"+Paths.get(path).getFileName().toString();
		}
		if(this.getStatus()==0){

			writeToFile(data,path);//Write decrypted file bytes to new path
			this.setEndTime(System.currentTimeMillis());
			long totalTime = this.getEndTime() - this.getStartTime();
		     log.info("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
		}
		else{
			log.info("Action fail for file"+this.getFilePath()+" due to exception:"+this.getError().getMessage());
					
		}

		end();

	}
	/*
	 * In case action is running as new thread
	 */
	public void run() {
		action();
		
	}



	}
