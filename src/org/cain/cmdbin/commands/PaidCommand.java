package org.cain.cmdbin.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class PaidCommand extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("paid")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						hasPaid(p, args[0]);
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					hasPaid(s, args[0]);
				}
			}
		}
		return false;
	}
	
	public void hasPaid(CommandSender p, String user)
	{
		try {
		    URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + user);

		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String str;
		    while ((str = in.readLine()) != null) {
		      p.sendMessage(str);
		    }
		    in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}

}
