import java.io.File;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

public class applicationMain {
	
	public static void main(String args[]) throws IOException, ParseException {
		
		Logger logger = Logger.getLogger(applicationMain.class);
		BasicConfigurator.configure();
		
		String usage = "java CranFiles Folder" + " [-cran Cran_File]\n\n"
				+ "This parse the Cran files and Indexes the at INDEX_PATH "
				+ "and in INDEX_PATH that can be searched with SearchFiles";
		String cranFolder = null;
		String docsPath = null;
		boolean create = true;
		for (int i = 0; i < args.length; i++) {
			if ("-cran".equals(args[i])) {
				cranFolder = args[i + 1].trim();
				i++;
			}
		}
		
		if (cranFolder == null) {
			System.err.println("Usage: " + usage);
			System.exit(1);
		}
		String fileName = null;
		if (cranFolder.endsWith("/")) {
			fileName=cranFolder.substring(0,cranFolder.length()-1);
		}
		else {
			fileName=cranFolder;
		}
		CreateFile cf;
		File folder = new File(fileName);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		for(int i = 0; i<listOfFiles.length; i++) {
//			System.out.println(listOfFiles[i].getName());
			if ("cran.all.1400".equals(listOfFiles[i].getName())) {
				logger.info("Parsing the file " + listOfFiles[i].getName());
				cf = new CreateFile();
				cf.createfile(listOfFiles[i].toString());
				logger.info("----Parsing complete----- ");
			}
			else if("cran.qry".equals(listOfFiles[i].getName())){
				logger.info("Parsing the file " + listOfFiles[i].getName());
				cf = new CreateFile();
				cf.createfile(listOfFiles[i].toString());
				logger.info("----Parsing complete for----- "  + listOfFiles[i].getName());
			}
		}
		logger.info("----Indexing in progress.... ");
		IndexFiles.callIndex("output1", "INDEX_PATH");
		logger.info("----Indexing complete----- ");
		logger.info("----------------------------------------------------------------------------- ");
		logger.info("----Search in progress.... ");
		File f = Search.search("INDEX_PATH");
		logger.info("----Searching complete----- ");
		logger.info("All Process Complete...");
		logger.info("PLease find the Result file at "+ f.getAbsolutePath());
	}

}
