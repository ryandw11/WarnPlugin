package me.ryandw11.ultrawarn.commands;


import me.ryandw11.ultrawarn.core.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand implements CommandExecutor {

	private Main plugin;
	public WarnCommand(Main plugin){
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		Player p = (Player) sender;
		
		if(!(p instanceof Player )) return true;
		
		if(cmd.getName().equalsIgnoreCase("warn")){
			if(args.length == 0){
				p.sendMessage(ChatColor.RED + "=============={" + ChatColor.WHITE + "Ultra Warn" + ChatColor.RED + "}==============");
				p.sendMessage(ChatColor.RED + "Plugin developed by:" + ChatColor.WHITE + " Ryandw11");
				p.sendMessage(ChatColor.RED + "Current Version: " + ChatColor.WHITE + String.format("%s", plugin.getDescription().getVersion()));
				p.sendMessage(ChatColor.RED + "Do " + ChatColor.WHITE + " /warn help " + ChatColor.RED + "for the list of commands.");
			}
			else if(args.length == 1 && args[0].equalsIgnoreCase("help")){
				p.sendMessage(ChatColor.RED + "==========={" + ChatColor.WHITE + "Ultra Warn Help" + ChatColor.RED + "}===========");
				p.sendMessage(ChatColor.WHITE + "/warn check {name}" + ChatColor.RED + " Check to see if a player has any warnings.");
				p.sendMessage(ChatColor.WHITE + "/warn {name} {Reason}" + ChatColor.RED + " Warn a player.");
				p.sendMessage(ChatColor.WHITE + "/warn remove {name}" + ChatColor.RED + " Remove a warning point.");
				p.sendMessage(ChatColor.WHITE + "/warn clear {name}" + ChatColor.RED + " Clear a players history.");
			}
			else if(args.length == 2 && args[0].equalsIgnoreCase("check")){
				if(p.hasPermission("ultrawarn.check")){
					Player pl = (Player) Bukkit.getServer().getPlayer(args[1]);
					if(!plugin.warn.contains(pl.getUniqueId().toString())){
						p.sendMessage(ChatColor.RED + "Error: That player does not exsist!");
					}//end of check
					else{
						p.sendMessage(ChatColor.RED + "========" + plugin.warn.getString(p.getUniqueId().toString() + ".name") + ChatColor.RED + "========");
						p.sendMessage(ChatColor.RED + "Warnings: " + ChatColor.WHITE + plugin.warn.getInt(p.getUniqueId().toString() + ".warnings"));
						p.sendMessage(ChatColor.RED + "Has been kicked: " + ChatColor.WHITE + plugin.warn.getString(p.getUniqueId().toString() + ".kicked"));
						p.sendMessage(ChatColor.RED + "Reports:");
						
					}
				}
			}//end
			
			else if(args.length >= 2){
				Player pl = (Player) Bukkit.getServer().getPlayer(args[0]);
				String reason = "";
				int warnings = plugin.warn.getInt(pl.getUniqueId() + "warnings");
				plugin.warn.set(pl.getUniqueId() + ".warnings", warnings++);
				plugin.saveFile();
				for (int i = 1; i < args.length; i++){
					reason = reason + " " + args[i];
					
				}
				pl.sendMessage(ChatColor.RED + "You have been warned for: " + ChatColor.WHITE + reason);
				p.sendMessage(ChatColor.GREEN + "You have warned " + pl.getName() + " for: " + reason);
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Warn]" + ChatColor.WHITE + pl.getName() + " has been warned for: " + ChatColor.RED + reason);
			}//end
			
		}
		return false;
	}

}
