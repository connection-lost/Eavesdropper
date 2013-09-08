package me.cap1tal.eavesdropper;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EavesdropperListener implements Listener
{
	Eavesdropper plugin;
	
	public EavesdropperListener(Eavesdropper instance)
	{
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
	{
		for (Player p : plugin.getEavesdroppers())
		{
			if (!p.equals(event.getPlayer()))
			{
				ChatColor c;
				
				c = ChatColor.DARK_RED;
				
				/* Change color depending on command. I guess this could be put into a config file */
				if (event.getMessage().startsWith("/tell ") || event.getMessage().startsWith("/w "))
				{
					c = ChatColor.AQUA;
				}
				
				p.sendMessage(c + event.getPlayer().getDisplayName() + ": " + event.getMessage());
			}
		}
	}
}
