package br.com.villagerid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class VillagerIdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		int totalGolens = 0;
		Player player = (Player) sender;
		Collection<Entity> entities = player.getNearbyEntities(256, 256, 256);
		EnumMap<Profession, Integer> professions = new EnumMap<>(Profession.class);
	
		for (Profession profession : Profession.values()) {
		    professions.put(profession, 0);
		}
	
		for (Entity entity : entities) {
			switch (entity.getType()) {
			case VILLAGER:
				Villager villager = (Villager) entity;
				switch (villager.getProfession()) {
				case ARMORER:
					professions.put(Profession.ARMORER, professions.get(Profession.ARMORER) + 1);
					break;
				case BUTCHER:
					professions.put(Profession.BUTCHER, professions.get(Profession.BUTCHER) + 1);
					break;
				case CARTOGRAPHER:
					professions.put(Profession.CARTOGRAPHER, professions.get(Profession.CARTOGRAPHER) + 1);
					break;
				case CLERIC:
					professions.put(Profession.CLERIC, professions.get(Profession.CLERIC) + 1);
					break;
				case FARMER:
					professions.put(Profession.FARMER, professions.get(Profession.FARMER) + 1);
					break;
				case FISHERMAN:
					professions.put(Profession.FISHERMAN, professions.get(Profession.FISHERMAN) + 1);
					break;
				case FLETCHER:
					professions.put(Profession.FLETCHER, professions.get(Profession.FLETCHER) + 1);
					break;
				case LEATHERWORKER:
					professions.put(Profession.LEATHERWORKER, professions.get(Profession.LEATHERWORKER) + 1);
					break;
				case LIBRARIAN:
					professions.put(Profession.LIBRARIAN, professions.get(Profession.LIBRARIAN) + 1);
					break;
				case MASON:
					professions.put(Profession.MASON, professions.get(Profession.MASON) + 1);
					break;
				case NITWIT:
					professions.put(Profession.NITWIT, professions.get(Profession.NITWIT) + 1);
					break;
				case SHEPHERD:
					professions.put(Profession.SHEPHERD, professions.get(Profession.SHEPHERD) + 1);
					break;
				case TOOLSMITH:
					professions.put(Profession.TOOLSMITH, professions.get(Profession.TOOLSMITH) + 1);
					break;
				case WEAPONSMITH:
					professions.put(Profession.WEAPONSMITH, professions.get(Profession.WEAPONSMITH) + 1);
					break;
				case NONE:
					professions.put(Profession.NONE, professions.get(Profession.NONE) + 1);
					break;
				default:
					Bukkit.getServer().getConsoleSender().sendMessage("ERRO: " + villager.getProfession() + " off switch");
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
	
		Integer totalOfVillagers = professions.values().stream().reduce(0, Integer::sum);
	
		sender.sendMessage("§a[TOTAL OF VILLAGERS]§r: " + totalOfVillagers);
		
		professions.forEach((profession, total) -> {
	
		    TextComponent message = new TextComponent(" * "+profession+": " + total);
		    message.setColor(ChatColor.GREEN);
		    List<Text> texts = new ArrayList<>();
		    
		    if (!profession.equals(Profession.NONE) && !profession.equals(Profession.NITWIT)) {
				List<Villager> villagersOfProfession  = entities.stream().filter(entity -> entity.getType().equals(EntityType.VILLAGER)).map(entity -> (Villager) entity).filter(villager -> villager.getProfession().equals(profession)).collect(Collectors.toList());
				texts.add(new Text("I: " + villagersOfProfession.stream().filter(villager -> villager.getVillagerLevel() == 1).count() + "\n"));
				texts.add(new Text("II: " + villagersOfProfession.stream().filter(villager -> villager.getVillagerLevel() == 2).count() + "\n"));
				texts.add(new Text("III: " + villagersOfProfession.stream().filter(villager -> villager.getVillagerLevel() == 3).count() + "\n"));
				texts.add(new Text("IV: " + villagersOfProfession.stream().filter(villager -> villager.getVillagerLevel() == 4).count() + "\n"));
				texts.add(new Text("V: " + villagersOfProfession.stream().filter(villager -> villager.getVillagerLevel() == 5).count()));
				message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, texts.toArray(Text[]::new)));
		    }
		    
		    player.spigot().sendMessage(message);
	
		});
	
		sender.sendMessage("§a[TOTAL OF GOLENS]§r: " + totalGolens);
		sender.sendMessage("§a[UNEMPLOYMENT RATE]§r: " + professions.getOrDefault(Profession.NONE, 0) * 100 / totalOfVillagers + "%");
	
		return true;
    }

}