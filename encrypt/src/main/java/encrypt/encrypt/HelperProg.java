package encrypt.encrypt;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;

public @Data class HelperProg implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		do{
		Object obj = null;
		String destination;
		int userChoiceAction=getActionrUserChoice();//Choose encrypt or decrypt
		int userChoiceAlgorithem=getAlgoUserChoice();//Choose algo for action
		if(userChoiceAlgorithem!=7){
			destination=getFilePath();
			obj=(IEncryptorDecryptor)obj;}
		else{
			destination=getDirectoryPath();
			obj=(MultipleFile)obj;
		}
		
		obj=createAnObject(userChoiceAction,userChoiceAlgorithem,destination);//Init object for action
		if(userChoiceAlgorithem!=7){
			((IEncryptorDecryptor) obj).action();//Action preform
		}
		else{
			((MultipleFile) obj).action();//Action preform
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}while(true);
		
	}
	/**
	 * @return user choice for an action
	 */
	@SuppressWarnings("resource")
	public int getActionrUserChoice(){
		int choiceInt;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		System.out.print("Hello! ");
		//Get user choice for action until right input received.
		do{
			System.out.println("Choose an action:\n1-Encryptor\n2-Decryptor");
			try{
				choiceInt=reader.nextInt();
				if(choiceInt==1){
					return 1;
				}else if(choiceInt==2){
					return 2;
				}else{
					System.out.println("Wrong value!");
					
				}

			}
			catch (InputMismatchException a) {
				System.out.println("This is not a number!");
				reader.next();
			}

		}while(true);


	}
	
	public int getAlgoUserChoice(){
		int choiceInt;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		//Get user choice for action until right input received.
		do{
			System.out.println("Choose an algorithem:\n1-Ceaser\n2-Xor\n3-Multiplication\n4-Double\n5-Reverse\n6-Split\n7-Directory");
			try{
				choiceInt=reader.nextInt();
				if(choiceInt>=1 && choiceInt<=7){
					return choiceInt;}
				else{
					System.out.println("Wrong value!");
					
				}

			}
			catch (InputMismatchException a) {
				System.out.println("This is not a number!");
				reader.next();
			}

		}while(true);

	}
	//Get file name for action
	/**
	 * @return path of file from user
	 */
	public String getFilePath(){
		String path="";
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		File file;
		do{
			System.out.println("Enter a file path:");
			path=reader.nextLine();
			file=new File(path);
		}while(!file.exists() || !file.isFile());
		return path;

	}
	//Get file name for action
		/**
		 * @return path of file from user
		 */
		public String getDirectoryPath(){
			String path="";
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			File file;
			do{
				System.out.println("Enter a directory path:");
				path=reader.nextLine();
				file=new File(path);
			}while(!file.exists() || !file.isDirectory());
			return path;

		}
	public Object createAnObject(int userChoiceAction,int userChoiceAlgorithem,String file){
		Object obj = null;
		switch (userChoiceAction){
    	case 1://encrypt
    	{
    		switch (userChoiceAlgorithem){
    			case 1:{
    				obj=new CeaserEncryptor(file);
    				break;
    			}
    			case 2:{
    				obj=new XorAlgorithemEncryptor(file);
    				break;
    			}
    			case 3:{
    				obj=new MultiplicationAlgorithemEncryptor(file);
    				break;
    			}
    			case 4:{
    				obj=new DoubleAlgorithemEncryptor(file);
    				break;
    				
    			}
    			case 5:{
    				obj=new ReverseAlgorithem(file,userChoiceAction);
    				break;
    				
    			}
    			case 6:{
    				obj=new SplitAlgorithemEncryptor(file);
    				break;
    				
    			}
    			case 7:{
    				obj=new MultipleFileEncryptor(file);
    				break;
    				
    			}
    		}
			break;

    	}
    	case 2://decrypt
    	{
    		switch (userChoiceAlgorithem){
    			case 1:{
    				obj=new CeaserDecryptor(file);
    				break;
    			}
    			case 2:{
    				obj=new XorAlgorithemDecryptor(file);
    				break;
    				}
    			case 3:{
    				obj=new MultiplicationAlgorithemDecryptor(file);
    				break;
    			}
    			case 4:{
    				obj=new DoubleAlgorithemDecryptor(file);
    				break;
    				
    			}
    			case 5:{
    				obj=new ReverseAlgorithem(file,userChoiceAction);
    				break;
    				
    			}
    			case 6:{
    				obj=new SplitAlgorithemDecryptor(file);
    				break;
    				
    			}
    			case 7:{
    				obj=new MultipleFileDecryptor(file);
    				break;
    				
    			}

    		}
			break;

    	}
	}
		return obj;
	}
}
