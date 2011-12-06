package com.bloodymercy.bmcommandbin.statistics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.bloodymercy.bmcommandbin.BMCommandBin;

public class Statistics
{

	public static void StartStats()
	{
		String url = String.format("http://cain.donaghey.x10.mx/usage/counter.php");
		// String url2 = String.format("http://cain.donaghey.x10.mx/usage/total.php");
		 
		 System.out.println(BMCommandBin.plugin.Plugin + "[Stats] Starting up CommandBin has been logged online!");
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
