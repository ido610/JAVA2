package encrypt.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lombok.Data;

public @Data class MultipleFileEncryptor extends MultipleFile {
	List<CeaserEncryptor> fileList = new ArrayList<CeaserEncryptor>();
	   static Logger log = Logger.getLogger(MultipleFileEncryptor.class.getName());

	public MultipleFileEncryptor(String file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	public  void action(){
		super.action();
		long startTime = System.currentTimeMillis();
	     log.info("Starting multiple encryption for files in folder: "+this.getDirectoryPath());
		//System.out.println("Starting multiple encryption for files in folder: "+this.getDirectoryPath());
		setKey();
		if(this.type==1){
			for(CeaserEncryptor ce:fileList){
				executor.execute(ce);
			}
            executor.shutdown();
            try {
            	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            	} catch (InterruptedException e) {
            	  log.info(e.getMessage());
            	}
			
		}else{
			for(CeaserEncryptor ce:fileList){
				ce.action();
			}
		}
		long endTime = System.currentTimeMillis();
		XMLReport();
		long totalTime = endTime - startTime;
		//System.out.println("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");
	     log.info("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");


	}

	@Override
	protected void initilizeListOfFiles() {
		File[] listOfFiles = directoryPath.listFiles();
		for(File path:listOfFiles){
			if (path.isFile()) {
		        this.fileList.add(new CeaserEncryptor(path.toString(),1));
		      } 
		}
		
	}
	/*
	 * Generate encryption key for all files
	 */
	private void setKey(){
		Random rn = new Random();
		int key=Math.abs(rn.nextInt());
		log.info("Key generated for multiple encryption is:"+key);
		if(fileList.size()>0){
			fileList.get(0).setKey(key);
			fileList.get(0).keyToBin(directoryPath+"/encrypted-decrypted/"+"key.bin");
		}
		
	}
	private void XMLReport(){
		try
		{
		  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		  //root elements
		  Document doc = docBuilder.newDocument();

		  Element rootElement = doc.createElement("Files");
		  doc.appendChild(rootElement);
		  for(CeaserEncryptor ce:fileList){
			  Element File = doc.createElement("File");
			  rootElement.appendChild(File);
			  
			  Attr nameAttr = doc.createAttribute("name");
			  nameAttr.setValue(ce.getFilePath().toString());
			  File.setAttributeNode(nameAttr);
			  
			  Element status = doc.createElement("status");
	
			  
			  if(ce.getStatus()==0){
				  status.appendChild(doc.createTextNode("Success"));
				  File.appendChild(status);
				  
				  Element time = doc.createElement("time");
				  long timee=(ce.getEndTime()-ce.getStartTime());
				  time.appendChild(doc.createTextNode(String.valueOf(timee)+"ms"));
				  File.appendChild(time);
				  
			  }else{
				  status.appendChild(doc.createTextNode("Fail"));
				  File.appendChild(status);
				  
				  
					Element exception = doc.createElement("Exception");
					File.appendChild(exception);
					
					
					Element expName = doc.createElement("ExceptionName");
					expName.appendChild(doc.createTextNode(ce.getError().getClass().getSimpleName()));
					exception.appendChild(expName);
					
					Element expMess = doc.createElement("ExceptionMessage");
					expMess.appendChild(doc.createTextNode(ce.getError().getMessage()));
					exception.appendChild(expMess);
					
					Element expSta = doc.createElement("ExceptionStack");
					expSta.appendChild(doc.createTextNode(ce.getError().getStackTrace().toString()));
					exception.appendChild(expSta);
				  
			  }
		  }

		  //write the content into xml file
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transformer = transformerFactory.newTransformer();
		  DOMSource source = new DOMSource(doc);

		  StreamResult result =  new StreamResult(new File(directoryPath+"/encrypted-decrypted/"+"Report.xml"));
		  transformer.transform(source, result);

		  System.out.println("Done creating report");

		}catch(ParserConfigurationException pce){
		  pce.printStackTrace();
		}catch(TransformerException tfe){
		  tfe.printStackTrace();
		}
		
		
	}

}
