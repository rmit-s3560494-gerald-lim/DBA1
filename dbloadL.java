import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dbloadL {

    public static void main(String[] args) throws FileNotFoundException 
    {
		String fileName = "/Users/gerald/Google Drive/Database Systems/A1/BUSINESS_NAMES_201803.csv";
		File file = new File(fileName);
		
		List<List<String>> lines = new ArrayList<>();
		
		Scanner inputStream;
		
		try {
			inputStream = new Scanner(file);
			
			while(inputStream.hasNext()) {
				String line = inputStream.next();
				String[] values = line.split("\t");
				lines.add(Arrays.asList(values));
			}
			inputStream.close();
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		
		int lineNo = 1;
		for (List<String> line: lines)
		{
			int columnNo = 1;
			for (String value: line)
			{
				System.out.println(lineNo + " " + columnNo + " " + value);
				columnNo++;
			}
			lineNo++;
		}
		
	}

}