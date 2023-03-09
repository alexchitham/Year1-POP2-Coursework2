import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PopThread implements Runnable
{	
	private static ArrayList<String> results; 
	private ArrayList<String> files;
	
	public PopThread(ArrayList<String> files)
	{
		this.files = files;
		PopThread.results = new ArrayList<String>();
	}
	
	
	public void run()
	{
		for (int i = 0; i < files.size(); i++)
		{
			String filename = files.get(i);
			int chr;
			char hash = '#';
			String text = "";
			String index = "";
			String chrBefore = "";
			
			try (FileReader input = new FileReader(filename); BufferedReader file = new BufferedReader(input))
			{
				while ((chr = file.read()) != (int) hash)
				{
					chrBefore = Character.toString((char) chr);
					text = text + Character.toString((char) chr);
				}
				
				index = index + file.readLine();
				text = text + "#" + index + chrBefore;
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			String strTotal = index.substring(4);
			String strNum = index.substring(0, 3);
			int total = Integer.parseInt(strTotal);
			int num = Integer.parseInt(strNum);
			
			if ((PopThread.results).size() == 0)
			{
				for (int x = 0; x < total; x++)
				{
					(PopThread.results).add("");
				}
			}
			PopThread.results.set(num - 1, text);
		}
		
		for (int y = 0; y < (PopThread.results).size(); y++)
		{
			if ((PopThread.results).get(y) == "")
			{
				return;
			}
		}
		
		try
		{
			File result = new File("result.txt");
			if (!result.exists())
			{
				result.createNewFile();
			}
			
			try (FileWriter out = new FileWriter(result); BufferedWriter output = new BufferedWriter(out))
			{
				for (int z = 0; z < (PopThread.results).size(); z++)
				{
					output.write((PopThread.results).get(z));
				}
			}
			
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		
	}

	
	
	public static void main(String[] args) 
	{
		ArrayList<String> filesOne = new ArrayList<String>();
		filesOne.add("1831-06-01.txt");
		filesOne.add("2003-08-27.txt");

		ArrayList<String> filesTwo = new ArrayList<String>();
		filesTwo.add("1961-04-12.txt");
		filesTwo.add("1972-12-11.txt");

		int numAttempts = 1;

		for(int i = 0; i < numAttempts; i++) {
		    System.out.println("Run: " + (i+1));
		    PopThread popRunnableOne = new PopThread(filesOne);
		    PopThread popRunnableTwo = new PopThread(filesTwo);
		    Thread threadOne = new Thread(popRunnableOne);
		    Thread threadTwo = new Thread(popRunnableTwo);
		    threadOne.start();
		    threadTwo.start();
		    try {
		        threadOne.join();
		        threadTwo.join();
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    //printContents("result.txt");
		}

	}

}
