package encrypt.encrypt;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import lombok.Data;

/**
 * @author עידו
 *
 */
public @Data abstract class MultipleFile {
    private static final int NTHREDS = 15;
	protected File directoryPath;//Directory of files to be in action
	protected int type;//Action type:1-async 2-sync
	protected ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);//Pool thread size 15
	public MultipleFile(String file) {
		this.directoryPath=new File(file);
	}

	public  void action(){
		chooseType();
		createDirectory();
		initilizeListOfFiles();

	}
	
	/**
	 * Choose the type of action for the action:async or sync
	 */
	private void chooseType(){
		int choiceInt;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		//Get user choice for action until right input received.
		do{
			System.out.println("Choose:\n1-async\n2-sync");
			try{
				choiceInt=reader.nextInt();
				if(choiceInt>=1 && choiceInt<=2){
					this.type=choiceInt;
					return;}
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
	
	/**
	 * Making a list of all files to be in action in the directory
	 */
	protected abstract void initilizeListOfFiles();
	
	
	/**
	 * Creating the directory for the files after action
	 */
	private void createDirectory(){
		File theDir = new File(directoryPath+"/encrypted-decrypted");
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.toString());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("Directory created");  
		    }
		}else{
	        System.out.println("Directory already exist");  
		}
	}

}
