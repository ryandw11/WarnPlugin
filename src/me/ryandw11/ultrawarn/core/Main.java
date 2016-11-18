package me.ryandw11.ultrawarn.core;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.ryandw11.ultrawarn.commands.WarnCommand;
import me.ryandw11.ultrawarn.listner.OnJoin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public Main plugin;
	
	public File warnfile = new File(getDataFolder() + "/warnings.yml");
	public FileConfiguration warn = YamlConfiguration.loadConfiguration(warnfile);
	
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(String.format("[UltraWarn] is enabled and running fine! V: %s", getDescription().getVersion())); // prints into the log
		loadMethoid();
		loadFile();
		registerConfig();
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info("[UltraWarn] has been disabled correctly!"); // same thing
		saveFile();
	}
	
	
	
	
	
	
	
	private void registerConfig() {
		saveDefaultConfig();
		
	}
	
	
	
	
	
	
	
	
	public void saveFile(){
		
		try{
			warn.save(warnfile);
		}catch(IOException e){
			e.printStackTrace();
			
		}
		logger.info("[UltraWarn] The file: warning.yml was saved.");
		
	}
	
	public void loadFile(){
		if(warnfile.exists()){
			try {
				warn.load(warnfile);
				
			} catch (IOException | InvalidConfigurationException e) {

				e.printStackTrace();
			}
			logger.info("[UltraWarn] Loaded the file: warnings.yml.");
		}
		else{
			try {
				warn.save(warnfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("[UltraWarn] Created the file: warnings.yml.");
		}
	}
	
	public void loadMethoid(){
		Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(this), this);
		getCommand("warn").setExecutor(new WarnCommand(this));
		
	}

}

//Permmisions: