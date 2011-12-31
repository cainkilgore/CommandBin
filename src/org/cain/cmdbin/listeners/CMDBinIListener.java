package org.cain.cmdbin.listeners;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.cain.cmdbin.CommandBin;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CMDBinIListener extends InputListener {
	
	Keyboard input = Keyboard.KEY_BACKSLASH;
	ScreenType ingame = ScreenType.GAME_SCREEN;
	boolean enabled = false;
	
	public void onKeyPressedEvent(KeyPressedEvent e) {
		if(e.getScreenType() == ingame) {
			if(e.getKey() == input) {
				if(Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
					if(enabled == false) {
						e.getPlayer().chat("//");
						enabled = true;
					} else {
						e.getPlayer().chat("//");
						enabled = false;
					}
				} else {
					e.getPlayer().sendMessage(ChatColor.RED + "Error: WorldEdit is not detected on this server.");
				}
			}
			
		if(e.getKey() == Keyboard.KEY_R) {
				drawGUI(e.getPlayer());
		}
	}
		return;
	}
	
	public void drawGUI(SpoutPlayer sp) {
		
		GenericTexture tp = new GenericTexture();
		tp.setUrl("http://dl.dropbox.com/u/7186172/CommandBin.png");
		tp.setHeight(98);
		tp.setWidth(664);
		tp.setAnchor(WidgetAnchor.TOP_LEFT);
		tp.setPriority(RenderPriority.Highest);
		
		GenericPopup gp = new GenericPopup();
		gp.setX(20);
		gp.setY(20);
		gp.setWidth(sp.getMainScreen().getWidth());
		gp.setHeight(sp.getMainScreen().getHeight());
		gp.setAnchor(WidgetAnchor.TOP_LEFT);
		gp.setBgVisible(true);
		gp.attachWidget(CommandBin.cmdbin, tp);
		gp.attachWidget(CommandBin.cmdbin, NewLabel(10, 110, "CommandBin - Created by CainFoool"));
		gp.attachWidget(CommandBin.cmdbin, NewLabel(10, 130, "This server is running: " + CommandBin.cmdbin.getDescription().getVersion()));
		gp.attachWidget(CommandBin.cmdbin, NewLabel(10, 150, "Go to http://bukkit.org to view the thread of CommandBin"));
		gp.attachWidget(CommandBin.cmdbin, NewLabel(10, 170, "Press ESC to back out of this screen"));
		gp.attachWidget(CommandBin.cmdbin, NewTextField(0, 190, sp, DeveloperMessage()));
		sp.getMainScreen().attachPopupScreen(gp);
	}
	
	public GenericButton MakeNewButton(String text, WidgetAnchor wa, int width, int height, boolean enabled) {
		GenericButton b = new GenericButton();
		b.setText(text);
		b.setAlign(wa);
		b.setAnchor(wa);
		b.setWidth(width);
		b.setEnabled(enabled);
		b.setHeight(height);
		b.setPriority(RenderPriority.Lowest);
		return b;
	}
	
	public GenericLabel NewLabel(int posx, int posy, String text) {
		GenericLabel l = new GenericLabel();
		l.setText(text);
		l.setAlign(WidgetAnchor.CENTER_LEFT);
		l.setX(posx);
		l.setY(posy);
		l.setHeight(20);
		l.setPriority(RenderPriority.Low);
		return l;
	}
	
	public GenericTextField NewTextField(int x, int y, SpoutPlayer p, String text) {
		GenericTextField tf = new GenericTextField();
		tf.setWidth(p.getMainScreen().getWidth());
		tf.setHeight(15);
		tf.setX(x);
		tf.setY(y);
		tf.setBorderColor(new Color(0, 0, 0));
		tf.setPlaceholder(text);
		return tf;
	}
	
	public String DeveloperMessage() {
			try {
				URL url = new URL("http://dl.dropbox.com/u/7186172/CommandBin/message.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				String strLine;
				while((strLine = br.readLine()) != null) {
						return strLine;
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

}
