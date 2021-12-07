package br.com.villagerid;

import org.bukkit.plugin.java.JavaPlugin;

public class Commands extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getCommand("getVillage").setExecutor(new VillagerIdCommand());
	}

	@Override
	public void onDisable() {

	}

}
