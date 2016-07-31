package encrypt.encrypt;

import java.nio.file.Path;
import java.util.logging.Logger;

import lombok.Data;

public @Data class XorAlgorithemDecryptor extends XorAlgorithem {
	   static Logger log = Logger.getLogger(XorAlgorithemDecryptor.class.getName());

	public XorAlgorithemDecryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public XorAlgorithemDecryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		starter();
		//safePrintln("Start xor decryption for file: "+getFilePath());
	     log.info("Start xor decryption for file: "+getFilePath());

		byte[] data=decryptData();//Get file content
		long startTime = System.currentTimeMillis();
		data=xorAction(data);//Decrypt each byte
			String[] tokens = getFilePath().toString().split("\\.(?=[^\\.]+$)");
		tokens=tokens[0].split("\\.(?=[^\\.]+$)");
		String path=tokens[0]+"_decrypted."+tokens[1];
		writeToFile(data,path);//Write decrypted bytes to file
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		//safePrintln("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
	      log.info("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");

		end();
	}
	public void run() {

		action();
	}

}
