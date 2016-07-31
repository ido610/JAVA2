/**
 * 
 */
package encrypt.encrypt;

import java.nio.file.Path;
import java.util.Random;
import java.util.logging.Logger;

import lombok.Data;

/**
 * @author עידו
 *
 */
public @Data class MultiplicationAlgorithemEncryptor extends MultiplicationAlgorithem {
	   static Logger log = Logger.getLogger(MultiplicationAlgorithemEncryptor.class.getName());

	/**
	 * @param path
	 */
	public MultiplicationAlgorithemEncryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public MultiplicationAlgorithemEncryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		starter();
		//safePrintln("Start Multiplication encryption for file: "+getFilePath());
	     log.info("Start Multiplication encryption for file: "+getFilePath());

		long startTime = System.currentTimeMillis();
		byte[] data=encryptData();//Get file byte's array
		if(data!=null){
		data=MultiplicationAction(data);//Encrypte bytes array
		String path=getFilePath().toString()+".encrypted";
		writeToFile(data,path);//Write encrypted bytes to path
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
	//	safePrintln("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
	     log.info("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");

		end();
		}
	}
	@Override
	public byte[] encryptData(){
		try {
			if(this.getMultipleFiles()==1){
				keyFromFile("key.bin");
			}else{
			setKey(getKeyMultiplication());}
			safePrintln("The key that was generate is:"+getKey());
			keyToBin("key.bin");
			return readFromFile();	
		} catch (IllegalKeyException e) {
			// TODO Auto-generated catch block
			safePrintln(e.getMessage());
			return encryptData();
		}
	}
	public int getKeyMultiplication() throws IllegalKeyException{
		Random rn = new Random();
		int key;
		key=Math.abs(rn.nextInt());
		if(key%2==0 || key<=0){
			throw new IllegalKeyException("key:"+key+" is illegal\n");
		}
		return key;
	}
	public void run() {
		action();
		
	}
}
