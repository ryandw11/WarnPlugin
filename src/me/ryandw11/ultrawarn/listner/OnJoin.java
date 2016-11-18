package me.ryandw11.ultrawarn.listner;



import java.util.ArrayList;
import java.util.List;




import me.ryandw11.ultrawarn.core.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
	private Main plugin;
	public OnJoin(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event){
		Player p = event.getPlayer();
		if(!plugin.warn.contains(p.getUniqueId().toString())){
			
			plugin.warn.set(p.getUniqueId().toString() + ".name", p.getDisplayName());
			plugin.warn.set(p.getUniqueId().toString() + ".warnings", 0);
			plugin.warn.set(p.getUniqueId().toString() + ".kicked", "false");
			
//			List<String> reasons = new ArrayList<String>();
//			reasons.add("");
//			plugin.warn.set(p.getUniqueId().toString() + ".reasons", reasons);
			plugin.saveFile();
		}else{
			plugin.warn.set(p.getUniqueId().toString() + ".name", p.getDisplayName());
			plugin.saveFile();
//			String points = "";
//			points = Integer.toString(plugin.warn.getInt(p.getUniqueId().toString() + ".warnings"));
//			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("On_Sever_Join").replace("{warn_points}", points).replace("{kick_points}", plugin.getConfig().getString("Kick_Points")).replace("{ban_points}", plugin.getConfig().getString("Ban_Points")).replace("{player}", p.getDisplayName())));
		}
		
	}
}
