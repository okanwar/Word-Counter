/**
 * Title: ChangeStyle
 * 
 * Authors: Chris Eardensohn (ceardensohn@sandiego.edu), 
 * 			Om Kanwar (okanwar@sandiego.edu)
 * 
 * Date: 9/29/17
 * 
 * Description: To read from a text file and change the position of the curly braces
 * 				in the file.
 */



import java.util.*;
import java.io.*;

	public class ChangeStyle
		{
			public static void main(String[] args)
				throws Exception
			{
			System.out.println("Enter Filename: "); 
			// Prompt user for a file name and get file name from console
			Scanner sc = new Scanner(System.in);
			String filename = new String(sc.nextLine());
			File file = new File(filename);	
				while (file.exists() == false)
				//loop to ensure a valid file name is entered
					{
						System.out.println("Bad file name");
						filename = sc.next();
						file = new File(filename);
					}
						sc.close();
					ArrayList<String> obj = new ArrayList<String>();
					//create array list to store read in lines from file
					String line1 = "";
					String line2 = "";
					Scanner scfile = new Scanner(file);
					line1 = scfile.nextLine();
					//reading from the file one line at a time
					while (scfile.hasNextLine() == true)
					//loop to read in lines until no lines left to read from file
					{
						line2 = scfile.nextLine();
	
					if (line2.length() > 0 && line2.charAt(line2.length() -1) == '{')
					//check to see if there is a '{' by itself on a line and
					//moving the curly brace to the previous line if true
						{
						obj.add(line1.concat(" {"));
						line1 = scfile.nextLine();
						}
					else
					//adds lines to array list if they don't have a lone curly brace
					{
						obj.add(line1);
						line1 = line2;
						}
					}
					obj.add(line1);
					//writes array list back into same file with curly braces in the
					//correct locationx
					PrintWriter output = new PrintWriter(filename);
		
					for(int i = 0; i < obj.size(); i++)					
						{
							output.println(obj.get(i));
						}
						output.close();
				} 
			}			