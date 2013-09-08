package me.cap1tal.eavesdropper;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Eavesdropper extends JavaPlugin
{
	private EavesdropperListener listener = new EavesdropperListener(this);
	private ArrayList<Player> eavesdropperList = new ArrayList<Player>();
	
	@Override
	public void onEnable()
	{
		PluginManager pm = this.getServer().getPluginManager();
		
		/* Add listener */
		pm.registerEvents(listener, this);
	}
	
	@Override
	public void onDisable()
	{
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player p;
		
		/* Make sure that the sender is a player */
		if (!(sender instanceof Player))
		{
			sender.sendMessage("You must be a player to use this command.");
			return true;
		}
		
		p = (Player)sender;
		
		/* Act according to command */
		if (cmd.getName().equalsIgnoreCase("listen"))
		{
			if (eavesdropperList.contains(p))
			{
				/* Remove them from the eavesdropper list */
				eavesdropperList.remove(p);
				
				p.sendMessage(ChatColor.GREEN + "You are no longer eavesdropping.");
			}
			else
			{
				/* Add them to the eavesdropper list */
				eavesdropperList.add(p);
				
				p.sendMessage(ChatColor.BLUE + "You are now eavesdropping.");
			}
		}
		
		return true;
	}
	
	public ArrayList<Player> getEavesdroppers()
	{
		return eavesdropperList;
	}
}
