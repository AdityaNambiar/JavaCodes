/* Tasks pending: 

	- To accept image filename from user.
		-- Be sure to take care of those width and height parameters.
	- To complete the decryptBytesToImage() method i.e. the decryption method 
		-- this method should "create" an output file. Reference are those 'pinned' tabs you have on chrome.

	P.S.: Don't focus on semantics and why-use-this-when-you-can-use-this type of questions. JUST use them..
*/
import java.io.*;  
//import java.util.Base64;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

class EncryptionProcess
{
	public byte[] encryptImageFromBytes(byte[] b)
	{
     /*
		Base64.Encoder encoder = Base64.getMimeEncoder();// getting Mime encoder to encode using normal Base64 encoding scheme.
		byte[] eByteArr = encoder.encode(b);
	*/
		byte[] eByteArr = Base64.encodeBase64(b);
		//System.out.println("\n Encoded Byte Array: " + eByteArr);
		return eByteArr;
	}
	
	void decryptBytesToImage(byte[] b) throws IOException
	{
	/*
        Base64.Decoder decoder = Base64.getMimeDecoder();// getting Mime decoder to decode using normal Base64 encoding scheme.
		byte[] dByteArr = decoder.decode(b);
	*/	
		byte[] dByteArr = Base64.decodeBase64(b);
		System.out.println("Decrypted original byte array(expected to return the \"original\" byte arr): "+dByteArr);
		//BufferedImage opimg = ImageIO.read(new ByteArrayInputStream(dByteArr));
		//File op = new File("2op.jpg");
		try 
		{
			// = ImageIO.read();
			//ImageIO.write(opimg, "jpg", op); // Parameters: bf = BufferedImage image object, "..." = image extension, f = fetching file name (+path) (?)
			FileOutputStream imageOutFile = new FileOutputStream("2op.jpg");
			imageOutFile.write(dByteArr);
			imageOutFile.close();
			System.out.println("Decoding Complete!");
		}
		catch(IOException e)
		{
			System.out.println("ErrorInDecoding: "+e);
			e.printStackTrace();
		}
	}
}

class Base64Encrypt 
{  
     	public static void main(String args[])
		{    	
				EncryptionProcess ep = new EncryptionProcess();
				File file = new File("2.jpg"); // create a file object and fetch the required/desired file via File() constructor parameter.
         	 	try{    
           			FileInputStream fin=new FileInputStream(file); 	//We are using "FileInputStream" object to fetch the data stream from the specified file.   
            		byte[] byteArr = new byte[(int)file.length()];  // Declaraing a byte array so as to store the Data Stream. Remember: any read/write operations on files deals with "byte" datatype.
					
					//ByteArrayInputStream bytIP = new ByteArrayInputStream(byteArr);
					System.out.println("Original Byte arr: "+byteArr);
					byte[] encryptedByteArr = ep.encryptImageFromBytes(byteArr); // Calls and passes byte array of image to the method definition in EncryptionProcess class.
					System.out.println("encrypted byte[] = "+encryptedByteArr);
					ep.decryptBytesToImage(encryptedByteArr);
					
					
					/*  Basic process: Reading the content of image in integers, nothing special as of now
					
					int i; //= fin.read(); // Reading the file now.
					while((i=fin.read(byteArr))!=(int)file.length()) //optimized way to simultaniously read the byte array
					{
						System.out.print(i); // Not typecasting "i" causes it to implicitly type cast i as seen on line no. 18...apparently.   
					}
					*/
					fin.close();    // file close method. Remember to close any I/P or O/P file you opened.
          		}catch(Exception e){System.out.println("ErrorInMain: "+e);
					e.printStackTrace();
				}    
        }    
}

