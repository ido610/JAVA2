package encrypt.encrypt;

/**
 * @author עידו
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelperProg hel=new HelperProg();//Object for running the program
		Thread runner=new Thread(hel);
		runner.start();
		
	

	}

}
