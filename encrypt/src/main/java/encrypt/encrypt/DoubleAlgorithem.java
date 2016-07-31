package encrypt.encrypt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;

public @Data abstract class DoubleAlgorithem implements IEncryptorDecryptor {
	SimpleAlgorithem a;
	SimpleAlgorithem b;
	Path path;

	public DoubleAlgorithem(String path) {
		this.path = Paths.get(path);
		// TODO Auto-generated constructor stub
	}
	public DoubleAlgorithem(Path path) {
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	protected void chooseEncryptorDecryptorAlgo() {
		int choiceInt1 = 0,choiceInt2=0;
		boolean flag=false;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		//Get user choice for action until right input received.
		do{
			System.out.println("Choose algorithem 1 for action:\n1-Ceaser\n2-Xor\n3-Multiplication");
			try{
				choiceInt1=reader.nextInt();
				if(choiceInt1>=1 && choiceInt1<=3){
					flag=true;
					}
				else{
					System.out.println("Wrong value!");
					
				}

			}
			catch (InputMismatchException a) {
				System.out.println("This is not a number!");
				reader.next();
			}

		}while(!flag);
		flag=false;
		//Get second algorithem
		do{
			System.out.println("Choose algorithem 2 for action:\n1-Ceaser\n2-Xor\n3-Multiplication");
			try{
				choiceInt2=reader.nextInt();
				if(choiceInt2>=1 && choiceInt2<=3 && choiceInt2!=choiceInt1){
					flag=true;
					}
				else{
					System.out.println("Wrong value!");
					
				}

			}
			catch (InputMismatchException a) {
				System.out.println("This is not a number!");
				reader.next();
			}

		}while(!flag);
		initAlgo(1,choiceInt1);//init the algorithems for action
		initAlgo(2,choiceInt2);
	}
	public abstract void initAlgo(int a,int b);//Diffrent action for encryption\decryption


}
