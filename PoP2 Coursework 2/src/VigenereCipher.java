import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VigenereCipher implements Cipher
{

	public String encrypt(String message_filename, String key_filename)
	{
		String[] strings = new String[2];
		strings = readFiles(message_filename, key_filename);
		String message = strings[0].toUpperCase();
		String key = strings[1].toUpperCase();
		
		int keyLen = key.length();
		int msgLen = message.length();
		int counter = 0;
		
		String encryptMsg = "";
		for (int i = 0; i < msgLen; i++)
		{
			char msgLetter = message.charAt(i);
			char keyLetter = key.charAt(counter);
			String newLetter = Character.toString(msgLetter);
			if (Character.isLetter(msgLetter) && Character.isLetter(keyLetter))
			{
				newLetter = shiftChar(keyLetter, ((int) msgLetter) - 65);
			}
			encryptMsg = encryptMsg + newLetter;
			
			counter++;
			if (counter == keyLen)
			{
				counter = 0;
			}
		}
		
		return encryptMsg;
	}
	
	public String decrypt(String message_filename, String key_filename)
	{
		String[] strings = new String[2];
		strings = readFiles(message_filename, key_filename);
		String message = strings[0].toUpperCase();
		String key = strings[1].toUpperCase();
		
		int keyLen = key.length();
		int msgLen = message.length();
		int counter = 0;
		
		String decryptMsg = "";
		for (int i = 0; i < msgLen; i++)
		{
			char msgLetter = message.charAt(i);
			char keyLetter = key.charAt(counter);
			String newLetter = Character.toString(msgLetter);
			
			if (Character.isLetter(msgLetter) && Character.isLetter(keyLetter))
			{
				int difference = (int) msgLetter - (int) keyLetter;
				if (difference < 0)
				{
					difference += 26;
				}
				difference += 65;
				newLetter = Character.toString((char) difference);
			}
			decryptMsg = decryptMsg + newLetter;
			
			counter++;
			if (counter == keyLen)
			{
				counter = 0;
			}
			
		}
		
		return decryptMsg;
	} 
	
	public String[] readFiles(String message_filename, String key_filename)
	{
		String[] strings = new String[2];
		try (FileReader msgIn = new FileReader(message_filename); BufferedReader msg = new BufferedReader(msgIn);
				FileReader keyIn = new FileReader(key_filename); BufferedReader key = new BufferedReader(keyIn))
		{
			String messageStr = "";
			String keyStr = "";
			int chr;
			String letter;
			
			while ((chr = msg.read()) != -1)
			{
				letter = Character.toString((char) chr);
				messageStr = messageStr + letter;
			}
			
			while ((chr = key.read()) != -1)
			{
				letter = Character.toString((char) chr);
				keyStr = keyStr + letter;
			}
			
			strings[0] = messageStr;
			strings[1] = keyStr;
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		
		return strings;
	}
	
	public String shiftChar(char letter, int shift)
	{
		int ascii = (int) letter;
		int newAscii = ascii + shift;
		if (newAscii < 65)
		{
			newAscii += 26;
		}
		else if (newAscii > 90)
		{
			newAscii -= 26;
		}
		String newLetter = Character.toString((char) newAscii);
		return newLetter;
	}
	
	
	public static void main(String[] args) 
	{
		VigenereCipher cipher = new VigenereCipher();
		//String[] string = new String[2];
		String string = cipher.encrypt("encrypt_check.txt", "key_check.txt");
		System.out.println(string);
		string = cipher.decrypt("decrypt_check.txt", "key_check.txt");
		System.out.println(string);
		//System.out.println((string[0]).length());
		//System.out.println("");
		//System.out.println(string[1]);
		//System.out.println((string[1]).length());
		
	}
}
