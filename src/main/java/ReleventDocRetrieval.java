
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReleventDocRetrieval {
	
	public static int releventDoc(String queryNum) throws IOException, FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader("cran/cranqrel"));
		String line=reader.readLine();
		int counter = 0;
		while (line != null) { 
			
			String a[] = line.split(" ");
			if (queryNum.equals(a[0])) {
//				if (a[2].equals("1") || a[2].equals("2") || a[2].equals("3")) {
//				counter++;
//				}
				counter++;
			}
			
			line=reader.readLine();
		}
		reader.close();
		return counter;
		
	}
	
//	public static void main(String args[]) throws FileNotFoundException, IOException  {
//		
//		System.out.println(releventDoc("3"));
//		
//	}

}
