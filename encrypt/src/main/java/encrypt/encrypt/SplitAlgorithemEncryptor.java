package encrypt.encrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

public @Data class SplitAlgorithemEncryptor extends SplitAlgorithem {

	public SplitAlgorithemEncryptor(Path path) {
		super(path, 1);
		// TODO Auto-generated constructor stub
	}
	public SplitAlgorithemEncryptor(String path) {
		super(path, 1);
		// TODO Auto-generated constructor stub
	}
	public void action() {
		byte[] odd,even,all;
		initAlgo(chooseAlgo());//Choose decryption algo
		 all=algo.readFromFile();
		odd=new byte[all.length/2];
		if(all.length%2==0){
		even=new byte[all.length/2];}
		else{
			even=new byte[(all.length/2)+1];
		}
		 for(int i=0;i<all.length;i++){//Split file to 2 byte arrays
			 if(i%2==0){
				 even[i/2]=all[i];
			 }else{
				odd[i/2]=all[i]; 
			 }
		 }
		 
		 even=subAction(even,"even","1");//get even bytes after action
		 odd=subAction(odd,"odd","2");//get odd bytes after action
		 		 

		 
		 
		 
		 for(int i=0;i<all.length;i++){//Return to 1 array
			 if(i%2==0){
				 all[i]=even[i/2];
			 }else{
				all[i]=odd[i/2]; 
			 }
		 }
		 
		 
		 algo.setFilePath(path);
		 algo.writeToFile(all, path.toString()+".encrypted");//Write encrypted result to file

	}
	
	protected byte[] subAction(byte[] arr,String extra,String x){
		 algo.writeToFile(arr,path.toString()+extra);
		 algo.setFilePath(Paths.get(path.toString()+extra));
		 algo.action();
		 algo.keyToBin("key"+x+".bin");
		 algo.setFilePath(Paths.get(path.toString()+extra+".encrypted"));
		 arr=algo.readFromFile();
		 try {
			Files.delete(Paths.get(path.toString()+extra+".encrypted"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			Files.delete(Paths.get(path.toString()+extra));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
