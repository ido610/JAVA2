package encrypt.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import lombok.Data;

public @Data class MultipleFileEncryptor extends MultipleFile {
	List<CeaserEncryptor> fileList = new ArrayList<CeaserEncryptor>();
	   static Logger log = Logger.getLogger(MultipleFileEncryptor.class.getName());

	public MultipleFileEncryptor(String file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	public  void action(){
		super.action();
		long startTime = System.currentTimeMillis();
	     log.info("Starting multiple encryption for files in folder: "+this.getDirectoryPath());
		//System.out.println("Starting multiple encryption for files in folder: "+this.getDirectoryPath());
		setKey();
		if(this.type==1){
			for(CeaserEncryptor ce:fileList){
				executor.execute(ce);
			}
            executor.shutdown();
            try {
            	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            	} catch (InterruptedException e) {
            	  log.info(e.getMessage());
            	}
			
		}else{
			for(CeaserEncryptor ce:fileList){
				ce.action();
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		//System.out.println("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");
	     log.info("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");


	}

	@Override
	protected void initilizeListOfFiles() {
		File[] listOfFiles = directoryPath.listFiles();
		for(File path:listOfFiles){
			if (path.isFile()) {
		        this.fileList.add(new CeaserEncryptor(path.toString(),1));
		      } 
		}
		
	}
	/*
	 * Generate encryption key for all files
	 */
	private void setKey(){
		Random rn = new Random();
		int key=Math.abs(rn.nextInt());
		log.info("Key generated for multiple encryption is:"+key);
		if(fileList.size()>0){
			fileList.get(0).setKey(key);
			fileList.get(0).keyToBin(directoryPath+"/encrypted-decrypted/"+"key.bin");
		}
		
	}

}
