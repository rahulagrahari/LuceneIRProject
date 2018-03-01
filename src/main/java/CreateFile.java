
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateFile {

	public void createfile(String fileName) {

		BufferedReader reader;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			String prevLine = "";
			String DocNum = ""; 
			String docLine;
			boolean newDocCreated = false;
			boolean isFirst = true;
			
			while (line != null) {
//				System.out.println(fileName);
				if (fileName.equals("cran/cran.qry")) {
//					System.out.println("-----------------"+line);
					if (newDocCreated == false) {
						if (Files.notExists(Paths.get("Query"))){
							Boolean success = (new File("Query")).mkdirs();
							if (!success) {
							    System.out.println("Directory creation failed");
							    System.exit(1);
							}
							}
					File file = new File("Query/" + "QueryList");
					writer = new PrintWriter(new FileWriter(file));
					newDocCreated = true;}
					if (line.startsWith(".")) {
						line = line.replaceAll("\\s+", "");
						char c = line.charAt(1);
						if (c == 'I') {
							if (isFirst != true) {
								writer.println();
							}
							writer.print(line.substring(2));
							writer.print(" ");
							isFirst = false;
							
						}
						else if (c == 'W') {
//							File file = new File("output/" + DocNum);
//							writer = new PrintWriter(new FileWriter(file));
//							prevLine = "";
//							writer.println(line);
							prevLine = ".W";
						}
						
					}
					else {
						if (! prevLine.equals(".W")) {
							writer.print(" ");
						}
						writer.print(line);
						prevLine = "";
					}
					
					}
				
				else {
				if (line.startsWith(".")) {
					line = line.replaceAll("\\s+", "");
					char c = line.charAt(1);
					
					if (c == 'I') {
						if (writer != null) {
							writer.flush();
						}
						DocNum = line.substring(2);
					}

					else if (c == 'T') {
//						
						prevLine = ".T";
					}
					
					else if (c == 'W') {
//						
						prevLine = ".W";
					}
					if(isFirst==false) {
					writer.println();
					}
				}

				else {
					if (prevLine.equals(".T")) {
						if (Files.notExists(Paths.get("output1"))){
							Boolean success = (new File("output1")).mkdirs();
							if (!success) {
							    System.out.println("Directory creation failed");
							    System.exit(1);
							}
						}
						File file = new File("output1/" + DocNum);
						writer = new PrintWriter(new FileWriter(file));
						prevLine = "";
						isFirst=false;
					}
					else if(prevLine.equals(".W")) {
						isFirst=true;
						prevLine = "";
					}
					writer.print(" "+line);
					
				}
				
			}
				line = reader.readLine();
			
			}
			reader.close();
			writer.flush();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

/*	public static void main(String args[]) {

		CreateFile cf = new CreateFile();
		cf.createfile("cran/cran.all.1400");

	}
*/
}
