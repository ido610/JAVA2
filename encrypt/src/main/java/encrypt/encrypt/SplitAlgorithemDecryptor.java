package encrypt.encrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

public @Data class SplitAlgorithemDecryptor extends SplitAlgorithem {

	public SplitAlgorithemDecryptor(Path path) {
		super(path, 2);
		// TODO Auto-generated constructor stub
	}
	public SplitAlgorithemDecryptor(String path) {
		super(path, 2);
		// TODO Auto-generated constructor stub
	}
	public void action() {
		byte[] odd,even,all;
		String[] tokens;
		String path;
		initAlgo(chooseAlgo());//Choose decryption algo
		
		 all=algo.readFromFile();//Split file to 2 byte arrays
		odd=new byte[all.length/2];
		if(all.length%2==0){
		even=new byte[all.length/2];}
		else{
			even=new byte[(all.length/2)+1];
		}		 for(int i=0;i<all.length;i++){
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
		 
		 
		 
		 
		tokens = getPath().toString().split("\\.(?=[^\\.]+$)");//Write decrypted result to file
		tokens=tokens[0].split("\\.(?=[^\\.]+$)");
		path=tokens[0]+"_decrypted."+tokens[1];
		algo.setFilePath(getPath());
		algo.writeToFile(all,path);

		
		
		
		
	}
	protected byte[] subAction(byte[] arr,String extra,String x){
		String[] tokens;
		String path;
		 algo.writeToFile(arr,getPath().toString()+extra);
		 algo.setFilePath(Paths.get(getPath().toString()+extra));
		 algo.action();
			tokens = (getPath().toString()+extra).split("\\.(?=[^\\.]+$)");
			tokens=tokens[0].split("\\.(?=[^\\.]+$)");
			path=tokens[0]+"_decrypted."+tokens[1];
		 algo.setFilePath(Paths.get(path));
		 arr=algo.readFromFile();
		 
		 try {
			Files.delete(Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			Files.delete(Paths.get(getPath().toString()+extra));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
		
	}

}
