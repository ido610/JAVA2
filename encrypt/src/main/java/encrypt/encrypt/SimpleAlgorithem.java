package encrypt.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import lombok.Data;


/**
 * @author עידו
 *
 */
public @Data abstract class SimpleAlgorithem implements IEncryptorDecryptor,Runnable {
    private Path filePath;
	private Path filePathAfter;
	private int key;
	private int multipleFiles;
	private int status;
	private String keyPath;
    static Logger log = Logger.getLogger(SimpleAlgorithem.class.getName());

	/**
	 * Create new object thar represent file for an action
	 * @param path
	 */
	public SimpleAlgorithem(String path) {
		this.filePath = Paths.get(path);
		this.multipleFiles=0;
		this.status=0;
	}
	public SimpleAlgorithem(Path path) {
		this.filePath = path;
		this.multipleFiles=0;
		this.status=0;
	}
	
	public SimpleAlgorithem(String path,int multipleFiles) {
		this.filePath = Paths.get(path);
		this.multipleFiles=multipleFiles;
		this.status=0;
	}
	public SimpleAlgorithem(Path path,int multipleFiles) {
		this.filePath = path;
		this.multipleFiles=multipleFiles;
		this.status=0;
	}
	public abstract void action();




	/**
	 * @param data
	 * @param path
	 * Write the byte string after the action in file
	 */
	protected synchronized  void writeToFile(byte[] data, String path) {
	    Random rand = new Random();
		try {
			Files.write(Paths.get(path), data);
			this.filePathAfter=Paths.get(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Unable to write to output file");
		}

		log.info("File saved in:" + path);

	}
	//For encryption
	public void setKey() {
		setKey(-1);
		boolean flag=false;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		do{
			safePrintln("Please enter a key number:");
			try{
				setKey(reader.nextInt());
				flag=true;
			}catch (InputMismatchException a) {
				safePrintln("This is not a number!");
				reader.next();
				//throw new InputMismatchException("The key is not a number");
			}

		}while(flag==false && getKey()<=0);
		}
	//For decryption

	public synchronized void setKey(int key) {
		this.key = key;
	}
	public  byte[] encryptData(){
		Random rn = new Random();
		if(this.getMultipleFiles()==1){
			keyFromFile(this.getFilePath().getParent()+"/encrypted-decrypted/"+"key.bin");
		}else if(this.getMultipleFiles()==2){
			keyFromFile(this.getFilePath().getParent()+"/key.bin");
		}
		else{
		
			int key=Math.abs(rn.nextInt());
			setKey(key);
			log.info("The key that was generate is:"+getKey());
			}
		return readFromFile();	
		
	}
	/*Write key to file using seralization*/
	public  void keyToBin(String to){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(to);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(getKey());
	         out.close();
	         fileOut.close();
	         safePrintln("Serialized key is saved in "+to);
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	/*Read bytes from file*/
	public synchronized byte[] readFromFile(){
		byte[] data = null;
		try {
			data = Files.readAllBytes(getFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
	 	     log.info("Unable to read file");
		}
		return data;
		
	}
	public  byte[] decryptData(){
		//setKey();
		if(this.getMultipleFiles()==1){
			keyFromFile(this.keyPath);
		}else{
			String path;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			safePrintln("Enter key path:");
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			path=reader.next();
			keyFromFile(path);
		}
		return readFromFile();	
	}
	/*
	 * Get key from the path and set him
	 */
	protected synchronized void keyFromFile(String path){
				int key = 0;
			      try
			      {
			         FileInputStream fileIn = new FileInputStream(path);
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         key = (Integer) in.readObject();
			         in.close();
			         fileIn.close();
			         //System.out.println("Key found is:"+key);
			      }catch(IOException i)
			      {
				 	     log.info("key not found");
			      }catch(ClassNotFoundException c)
			      {
			 	     log.info("key not found");
			    	  //safePrintln("key not found");
			         c.printStackTrace();
			      }
			      setKey(key);
	}
	/*
	 * Synchronized printing
	 */
	public void safePrintln(String s){
	 synchronized (System.out) {
		    System.out.println(s);
		  }
	}
	protected void starter(){
		
	}
	protected void end(){
		
	}
}
