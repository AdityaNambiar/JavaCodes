/*
	Source:
	https://www.dyclassroom.com/image-processing-project/how-to-read-and-write-image-file-in-java 	
*/

import java.io.*;  
import java.util.Base64;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;

class TestImageIOClass 
{
	public static void main(String args[])
	{
		BufferedImage bf = null; // For images.
		File f = null; // For handling files.
		
		int width = 963;    //width of the image
		int height = 640;   //height of the image
		
		// Reading an image:
		try 
		{
			f  = new File("2.jpg"); // Image file.
			bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // Forming image object. -- Only done once, just to read images.
			bf = ImageIO.read(f); // Passing the image file to image object -- Reading image.
			
			System.out.println("Reading Complete!");
		}
		catch(IOException e)
		{
			System.out.println("Error: "+e);
		}
		
		// Writing an image (1) -- To an empty image file, that I created in the folder before code execution.
		try 
		{
			f = new File("2op.jpg");  // Output image file.
			ImageIO.write(bf, "jpg", f); // Parameters: bf = BufferedImage image object, "..." = image extension, f = fetching file name (+path) (?)
			System.out.println("Writing Complete!");
		}
		catch(IOException e)
		{
			System.out.println("Error: "+e);
		}
	}
}