package encrypt.encrypt;

import java.nio.file.Path;
import java.util.logging.Logger;

import lombok.Data;

public @Data class XorAlgorithemEncryptor extends XorAlgorithem {
	   static Logger log = Logger.getLogger(XorAlgorithemEncryptor.class.getName());

	public XorAlgorithemEncryptor(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public XorAlgorithemEncryptor(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public	void action() {
		// TODO Auto-generated method stub
		//safePrintln("Start xor encryption for file: "+getFilePath());
	      log.info("Start xor decryption for file "+getFilePath());

		long startTime = System.currentTimeMillis();
		byte[] data=encryptData();
		keyToBin("key.bin");//Write key to file
		data=xorAction(data);//encrypt each byte
		String path=getFilePath().toString()+".encrypted";
		writeToFile(data,path);//Write encrypted file
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
	//	safePrintln("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");
	      log.info("Time for action for file:"+this.getFilePath()+" is:"+totalTime+"ms");

	}
	public void run() {
		action();
	}

}
