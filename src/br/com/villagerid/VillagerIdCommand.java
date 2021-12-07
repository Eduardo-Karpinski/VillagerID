package br.com.villagerid;

import java.util.Collection;
import java.util.EnumMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public class VillagerIdCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		int totalGolens = 0;
		Player player = (Player) sender;
		Collection<Entity> entities = player.getNearbyEntities(256, 256, 256);
		EnumMap<Profession, Integer> professions = new EnumMap<>(Profession.class);

		for (Entity entity : entities) {
			switch (entity.getType()) {
			case VILLAGER:
				Villager villager = (Villager) entity;
				switch (villager.getProfession()) {
				case ARMORER:
					professions.put(Profession.ARMORER, professions.getOrDefault(Profession.ARMORER, 0) + 1);
					break;
				case BUTCHER:
					professions.put(Profession.BUTCHER, professions.getOrDefault(Profession.BUTCHER, 0) + 1);
					break;
				case CARTOGRAPHER:
					professions.put(Profession.CARTOGRAPHER, professions.getOrDefault(Profession.CARTOGRAPHER, 0) + 1);
					break;
				case CLERIC:
					professions.put(Profession.CLERIC, professions.getOrDefault(Profession.CLERIC, 0) + 1);
					break;
				case FARMER:
					professions.put(Profession.FARMER, professions.getOrDefault(Profession.FARMER, 0) + 1);
					break;
				case FISHERMAN:
					professions.put(Profession.FISHERMAN, professions.getOrDefault(Profession.FISHERMAN, 0) + 1);
					break;
				case FLETCHER:
					professions.put(Profession.FLETCHER, professions.getOrDefault(Profession.FLETCHER, 0) + 1);
					break;
				case LEATHERWORKER:
					professions.put(Profession.LEATHERWORKER, professions.getOrDefault(Profession.LEATHERWORKER, 0) + 1);
					break;
				case LIBRARIAN:
					professions.put(Profession.LIBRARIAN, professions.getOrDefault(Profession.LIBRARIAN, 0) + 1);
					break;
				case MASON:
					professions.put(Profession.MASON, professions.getOrDefault(Profession.MASON, 0) + 1);
					break;
				case NITWIT:
					professions.put(Profession.NITWIT, professions.getOrDefault(Profession.NITWIT, 0) + 1);
					break;
				case SHEPHERD:
					professions.put(Profession.SHEPHERD, professions.getOrDefault(Profession.SHEPHERD, 0) + 1);
					break;
				case TOOLSMITH:
					professions.put(Profession.TOOLSMITH, professions.getOrDefault(Profession.TOOLSMITH, 0) + 1);
					break;
				case WEAPONSMITH:
					professions.put(Profession.WEAPONSMITH, professions.getOrDefault(Profession.WEAPONSMITH, 0) + 1);
					break;
				case NONE:
					professions.put(Profession.NONE, professions.getOrDefault(Profession.NONE, 0) + 1);
					break;
				default:
					Bukkit.getServer().getConsoleSender().sendMessage("Profession " + villager.getProfession() + " não consta no case");
					break;
				}
				break;
			case IRON_GOLEM:
				totalGolens++;
				break;
			default:
				break;
			}
		}

		sender.sendMessage("§a[TOTAL OF VILLAGERS]§r: " + professions.values().stream().reduce(0, Integer::sum));
		
		professions.forEach((profession, total) -> {
			sender.sendMessage("§a[TOTAL OF "+profession+"]§r: " + total);
		});
		
		sender.sendMessage("§a[TOTAL DE GOLENS]§r: " + totalGolens);

		return true;
	}

}
