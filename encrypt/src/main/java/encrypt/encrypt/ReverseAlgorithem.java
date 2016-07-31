package encrypt.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import lombok.Data;

public @Data class ReverseAlgorithem  implements IEncryptorDecryptor {

	SimpleAlgorithem a;
	Path filepath;
	int action;
	public ReverseAlgorithem(String path,int action) {
		String path1=path;
		File toCopy=new File(path1);
		if(action==1){
			path1=path1+".encrypted";
			File destCopy=new File(path1);
			try {
				FileUtils.copyFile(toCopy, destCopy);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.filepath = Paths.get(path1);
		this.action=action;
	}

	public void action() {
		initAlgo(chooseAlgo());
		a.setMultipleFiles(2);
		a.action();
		if(action==1){
			try {
				Files.delete(this.filepath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			String path=this.getFilepath().toString()+".encrypted";
			File toCopy=new File(path);
			String[] tokens = path.split("\\.(?=[^\\.]+$)");
			tokens=tokens[0].split("\\.(?=[^\\.]+$)");
			String path2=tokens[0]+"."+tokens[1];
			File destCopy=new File(path2);
			try {
				FileUtils.copyFile(toCopy, destCopy);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Files.delete(Paths.get(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	private void initAlgo(int choose){
		switch (this.action){
    	case 2://decrypt
    	{
    		switch (choose){
    			case 1:{
    				a=new CeaserEncryptor(this.getFilepath());
    				break;
    			}
    			case 2:{
    				a=new XorAlgorithemEncryptor(this.getFilepath());
    				break;
    			}
    			case 3:{
    				a=new MultiplicationAlgorithemEncryptor(this.getFilepath());
    				break;
    			}

    		}
			break;

    	}
    	case 1://encrypt
    	{
    		switch (choose){
    			case 1:{
    				a=new CeaserDecryptor(this.getFilepath());
    				break;
    			}
    			case 2:{
    				a=new XorAlgorithemDecryptor(this.getFilepath());
    				break;
    				}
    			case 3:{
    				a=new MultiplicationAlgorithemDecryptor(this.getFilepath());
    				break;
    			}

    		}
			break;

    	}
	}
		
		
	}
	private int chooseAlgo(){
		int choiceInt;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		//Get user choice for action until right input received.
		do{
			System.out.println("Choose an algorithem for "+this.action+" operation:\n1-Ceaser\n2-Xor\n3-Multiplication");
			try{
				choiceInt=reader.nextInt();
				if(choiceInt>=1 && choiceInt<=3){
					return choiceInt;}
				else{
					System.out.println("Wrong value!");
					
				}

			}
			catch (InputMismatchException a) {
				System.out.println("This is not a number!");
				reader.next();
			}

		}while(true);	}
	

}
