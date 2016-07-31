package encrypt.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import lombok.Data;

public @Data class MultipleFileDecryptor extends MultipleFile {
	List<CeaserDecryptor> fileList = new ArrayList<CeaserDecryptor>();
    static Logger log = Logger.getLogger(MultipleFileDecryptor.class.getName());

	public MultipleFileDecryptor(String file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	public  void action(){
		super.action();
		setKeyPath();
		long startTime = System.currentTimeMillis();
		//System.out.println("Starting multiple decryption for files in folder: "+this.getDirectoryPath());
	     log.info("Starting multiple decryption for files in folder: "+this.getDirectoryPath());

		if(this.type==1){//Case of async
			for(CeaserDecryptor ce:fileList){
				executor.execute(ce);//execute actions in pool
			}
            executor.shutdown();
            try {
            	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);//Wait for all actions to finish
            	} catch (InterruptedException e) {
            	  log.info(e.getMessage());
            	}
		}else{
			for(CeaserDecryptor ce:fileList){//In case of sync running each file seperate
				ce.action();
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
	     log.info("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");

		//System.out.println("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");
	}
	private void setKeyPath(){
		String path;
		System.out.println("Enter directory key path:");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		path=reader.next();
		for(CeaserDecryptor ce:fileList){
			ce.setKeyPath(path);
		}
	}
	protected void initilizeListOfFiles() {
		File[] listOfFiles = directoryPath.listFiles();//Get all files in directory
		for(File path:listOfFiles){
			String[] tokens = path.toString().split("\\.(?=[^\\.]+$)");
			if (path.isFile() && tokens[1].equals("encrypted") ) {//Decrypt only files that are encrypted
		        this.fileList.add(new CeaserDecryptor(path.toString(),1));
		      } 
		}		
	}


}
