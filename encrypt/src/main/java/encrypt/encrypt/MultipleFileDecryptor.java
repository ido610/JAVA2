package encrypt.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

public @Data class MultipleFileDecryptor extends MultipleFile {
	List<CeaserDecryptor> fileList = new ArrayList<CeaserDecryptor>();
    static Logger log = Logger.getLogger(MultipleFileDecryptor.class.getName());

	public MultipleFileDecryptor(String file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	public  void action(){
		super.action();
		setKeyPath();
		long startTime = System.currentTimeMillis();
		//System.out.println("Starting multiple decryption for files in folder: "+this.getDirectoryPath());
	     log.info("Starting multiple decryption for files in folder: "+this.getDirectoryPath());

		if(this.type==1){//Case of async
			for(CeaserDecryptor ce:fileList){
				executor.execute(ce);//execute actions in pool
			}
            executor.shutdown();
            try {
            	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);//Wait for all actions to finish
            	} catch (InterruptedException e) {
            	  log.info(e.getMessage());
            	}
		}else{
			for(CeaserDecryptor ce:fileList){//In case of sync running each file seperate
				ce.action();
			}
		}
		long endTime = System.currentTimeMillis();
		XMLReport();
		long totalTime = endTime - startTime;
	     log.info("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");

		//System.out.println("Time for encryption for folder:"+this.getDirectoryPath()+" is:"+totalTime+"ms");
	}
	private void setKeyPath(){
		String path;
		System.out.println("Enter directory key path:");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		path=reader.next();
		for(CeaserDecryptor ce:fileList){
			ce.setKeyPath(path);
		}
	}
	protected void initilizeListOfFiles() {
		File[] listOfFiles = directoryPath.listFiles();//Get all files in directory
		for(File path:listOfFiles){
			String[] tokens = path.toString().split("\\.(?=[^\\.]+$)");
			if (path.isFile() && tokens[1].equals("encrypted") ) {//Decrypt only files that are encrypted
		        this.fileList.add(new CeaserDecryptor(path.toString(),1));
		      } 
		}		
	}
	protected void XMLReport(){
		try
		{
		  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		  //root elements
		  Document doc = docBuilder.newDocument();

		  Element rootElement = doc.createElement("Files");
		  doc.appendChild(rootElement);
		  for(CeaserDecryptor ce:fileList){
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
