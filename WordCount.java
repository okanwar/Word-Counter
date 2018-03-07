/**
 * Title: WordCount
 * 
 * Authors: Chris Eardensohn (ceardensohn@sandiego.edu), 
 * 			Om Kanwar (okanwar@sandiego.edu)
 * 
 * Date: 9/29/17
 * 
 * Description: To print a word count and average length of
 * 				user given file.
 */
import java.util.*;
import java.io.*;
public class WordCount {

	public static void main(String[] args) 
			throws FileNotFoundException {
		
		
		System.out.println("Enter filename:");
		//prompt for new filename
		Scanner input = new Scanner(System.in);
		String fname = new String(input.nextLine());
		File file = new File(fname);
		//loop for new filename if incorrect format
		while (file.exists() == false 
				&& (fname.endsWith(".dat") == false 
				|| fname.endsWith(".txt") == false))
		{
			System.out.println("Invalid filename: valid"
					+ "types are .txt and .dat");
			System.out.println("Enter filename:");
			fname = input.next();
			file = new File(fname);
		}
		input.close();
		
		//local variables
		double wordCount = 0;
		double charCount = 0;
		double avgLength = 0.0;
		String readLine = "";
		
		//wordcount
		// split by " " count array length
		
		Scanner readFile = new Scanner(file);
		while (readFile.hasNextLine() == true)
		{
			readLine = readFile.nextLine();
			String[] res = readLine.split(" ");
			for (int i = 0; i < res.length; i++ )
			{
				wordCount++;
				//loop for character count
				for(int j = 0; j < res[i].length(); j++)
				{
					charCount++;
				}
			}
		}
		readFile.close();
		
		// calc avg
		//sum of str. length at each element
		// divide by word count
		avgLength =(charCount / wordCount);
		System.out.println(fname + ": " + (int)wordCount +
				" words, average word length = " + avgLength
				+ " characters.");
	}

}
