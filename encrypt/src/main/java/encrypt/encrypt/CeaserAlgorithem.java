package encrypt.encrypt;

import java.nio.file.Path;

import lombok.Data;

/**
 * @author עידו
 *Class represent Ceaser Algorithem
 */
public @Data abstract class CeaserAlgorithem extends SimpleAlgorithem {

	public CeaserAlgorithem(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	public CeaserAlgorithem(Path path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public CeaserAlgorithem(String path, int i) {
		super(path,i);
		}
	/**
	 * @param bi
	 * @return byte fixed between the currect range of bytes accoriding to overflow
	 */
	protected Byte byteAfterAction(int bi) {
		if (bi % 256 > 127) {
			bi = bi % 256 - 256;

		} else {
			bi = bi % 256;
		}
		Byte b;
		b = (byte) bi;
		return b;
	}
}
