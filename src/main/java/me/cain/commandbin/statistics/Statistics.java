package main.java.me.cain.commandbin.statistics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import main.java.me.cain.commandbin.CommandBin;

public class Statistics
{

	public static void StartStats()
	{
		String url = String.format("http://cain.donaghey.x10.mx/usage/counter.php");
		// String url2 = String.format("http://cain.donaghey.x10.mx/usage/total.php");
		 
		 System.out.println(CommandBin.plugin.Plugin + "[Stats] Starting up CommandBin has been logged online!");
		 try
			{
				new URL(url).openConnection().getInputStream();
			} catch (MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
