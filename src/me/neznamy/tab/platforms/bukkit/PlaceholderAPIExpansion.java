package me.neznamy.tab.platforms.bukkit;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.Shared;

public class PlaceholderAPIExpansion{

	private static PlaceholderExpansion exp;

	public static void register() {
		exp = new PlaceholderExpansion() {

			@Override
			public boolean persist(){
				return true;
			}
			@Override
			public boolean canRegister(){
				return true;
			}
			@Override
			public String getAuthor(){
				return Main.instance.getDescription().getAuthors().toString();
			}
			@Override
			public String getIdentifier(){
				return "tab";
			}
			@Override
			public String getVersion(){
				return Main.instance.getDescription().getVersion();
			}
			@Override
			public String onPlaceholderRequest(Player player, String identifier){
				if (player == null) return "";
				ITabPlayer p = Shared.getPlayer(player.getUniqueId());
				if (p == null) return "";

				for (EnumProperty property : EnumProperty.values()) {
					if (identifier.equals(property.toString())) {
						return p.properties.get(property.toString()).get();
					}
					if (identifier.equals(property.toString() + "_raw")) {
						return p.properties.get(property.toString()).getCurrentRawValue();
					}
				}
				return null;
			}
		};
		exp.register();
	}
	public static void unregister() {
		try {
			if (exp != null) PlaceholderAPI.unregisterExpansion(exp);
		} catch (Exception ExceptionThrownDueToExpansionUnregisterEventBeingCalledInServerShutdownThreadWhichIsNotMainThreadAnd1_14RequiresTheEventToBeCalledInTheMainThread) {
			Shared.error("Failed to unregister expansion for PlaceholderAPI", ExceptionThrownDueToExpansionUnregisterEventBeingCalledInServerShutdownThreadWhichIsNotMainThreadAnd1_14RequiresTheEventToBeCalledInTheMainThread);
		}
	}
}