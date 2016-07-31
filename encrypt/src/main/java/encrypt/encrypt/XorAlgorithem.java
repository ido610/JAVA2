package encrypt.encrypt;

import java.nio.file.Path;

import lombok.Data;

public @Data abstract class XorAlgorithem extends SimpleAlgorithem {

	public XorAlgorithem(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public XorAlgorithem(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	/*Xor operation for each byte with the key*/
	public byte[] xorAction(byte[] data){
		for(int i=0;i<data.length;i++){
			data[i]=(byte) (data[i]^getKey());
		}
		return data;
	}

}
