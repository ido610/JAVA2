/**
 * 
 */
package encrypt.encrypt;

import java.nio.file.Path;

import lombok.Data;

/**
 * @author עידו
 *
 */
public  abstract class MultiplicationAlgorithem extends SimpleAlgorithem {

	/**
	 * @param path
	 */
	public MultiplicationAlgorithem(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public MultiplicationAlgorithem(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see encrypt.encrypt.ActionPreformer#action()
	 */
	/**
	 * @param data
	 * @return byte after multiplication with the key
	 */
	public byte[] MultiplicationAction(byte[] data){
		for(int i=0;i<data.length;i++){
			data[i]=MWO(data[i],getKey());
		}
		return data;
	}
	public  byte MWO(byte a,int b){
		return ((byte)(a*b));
	}

}
