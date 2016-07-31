package encrypt.encrypt;

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;

public @Data abstract class SplitAlgorithem implements IEncryptorDecryptor {

	SimpleAlgorithem algo;
	Path path;
	int action;
	public SplitAlgorithem(String path,int action){
		this.path=Paths.get(path);
		this.action=action;
		
	}
	public SplitAlgorithem(Path path,int action){
		this.path=path;
		this.action=action;

	}
	
	protected void initAlgo(int choose){
		switch (this.action){
    	case 1://decrypt
    	{
    		switch (choose){
    			case 1:{
    				algo=new CeaserEncryptor(this.getPath());
    				break;
    			}
    			case 2:{
    				algo=new XorAlgorithemEncryptor(this.getPath());
    				break;
    			}
    			case 3:{
    				algo=new MultiplicationAlgorithemEncryptor(this.getPath());
    				break;
    			}

    		}
			break;

    	}
    	case 2://encrypt
    	{
    		switch (choose){
    			case 1:{
    				algo=new CeaserDecryptor(this.getPath());
    				break;
    			}
    			case 2:{
    				algo=new XorAlgorithemDecryptor(this.getPath());
    				break;
    				}
    			case 3:{
    				algo=new MultiplicationAlgorithemDecryptor(this.getPath());
    				break;
    			}


    		}
			break;

    	}
	}
		
		
	}
	protected abstract byte[] subAction(byte[] arr,String extra,String x);
	protected int chooseAlgo(){
		int choiceInt;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		//Get user choice for action until right input received.
		do{
			System.out.println("Choose an algorithem for operation:\n1-Ceaser\n2-Xor\n3-Multiplication");
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
