package me.neznamy.tab.shared;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.neznamy.tab.premium.Premium;
import me.neznamy.tab.shared.BossBar.BossBarLine;

public class Configs {

	public static ConfigurationFile config;
	public static HashMap<String, List<String>> configComments;
	public static boolean unlimitedTags;
	public static boolean modifyNPCnames;
	public static boolean collision;
	public static HashMap<String, String> sortedGroups;
	public static Map<String, Object> rankAliases;
	public static boolean doNotMoveSpectators;
	public static List<Object> disabledHeaderFooter;
	public static List<Object> disabledTablistNames;
	public static List<Object> disabledNametag;
	public static List<Object> disabledTablistObjective;
	public static List<Object> disabledBossbar;
	public static List<Object> disabledBelowname;
	public static String dateFormat;
	public static String timeFormat;
	public static double timeOffset;
	public static List<String> removeStrings = new ArrayList<String>();
	public static String noFaction;
	public static String yesFaction;
	public static String noTag;
	public static String yesTag;
	public static String noAfk;
	public static String yesAfk;


	public static ConfigurationFile animation;
	public static HashMap<String, List<String>> animationComments;
	public static List<Animation> animations;


	public static ConfigurationFile bossbar;
	public static HashMap<String, List<String>> bossbarComments;


	public static ConfigurationFile translation;
	public static String no_perm;
	public static String unlimited_nametag_mode_not_enabled;
	public static String data_removed;
	public static String player_not_found;
	public static String reloaded;
	public static String value_assigned;
	public static String value_removed;
	public static String plugin_disabled = "�c[TAB] Plugin is disabled because one of your configuration files is broken. Check console for more info.";
	public static List<Object> help_menu = new ArrayList<Object>();
	public static String bossbar_off;
	public static String bossbar_on;
	public static String preview_off;
	public static String preview_on;

	public static ConfigurationFile advancedconfig;
	public static HashMap<String, List<String>> advancedconfigComments;
	public static boolean sortByPermissions = false;
	public static boolean fixPetNames = false;
	public static boolean usePrimaryGroup = true;
	public static List<? extends Object> primaryGroupFindingList = Lists.newArrayList("Owner", "Admin", "Helper", "default");


	public static File errorFile = new File(ConfigurationFile.dataFolder, "errors.txt");

	static {
		configComments = new HashMap<String, List<String>>();
		configComments.put("nametag-refresh-interval-ticks", Lists.newArrayList("", "#20 ticks = 1 second"));
		configComments.put("tablist-objective:", Lists.newArrayList("#the yellow number in tablist", "#options: PING, HEARTS, NONE (to disable it), CUSTOM (any placeholder defined below)"));
		configComments.put("tablist-objective-value:", Lists.newArrayList("#the yellow number in tablist", "#set to \"\" to disable"));
		configComments.put("group-sorting-priority-list", Lists.newArrayList("#NOT case sensitive"));
		configComments.put("Groups:", Lists.newArrayList("#properties: tabprefix, tabsuffix, tagprefix (in name tag), tagsuffix, customtabname (modifying the name itself), header, footer", "#extra ones which need unlimited nametag mode enabled: abovename (line of text above name tag), belowname (below name tag), customtagname"));
		configComments.put("  _OTHER_:", Lists.newArrayList("  #any other group not defined above"));
		configComments.put("Users:", Lists.newArrayList("", "#personal settings, override group settings"));
		configComments.put("enable-collision:", Lists.newArrayList("", "#servers and clients 1.9+"));
		configComments.put("do-not-move-spectators", Lists.newArrayList("", "#preventing players in spectator gamemode from appearing at the bottom of tablist with transparent name FOR OTHER PLAYERS"));
		configComments.put("unlimited-nametag-prefix-suffix-mode:", Lists.newArrayList("#VERY EXPERIMENTAL !", "#IF YOU EXPERIENCE ANY ISSUES CONTACT ME"));
		configComments.put("  modify-npc-names:", Lists.newArrayList("  #modifying names of NPCs to avoid empty names on NPCs of online players", "  #this blocks any other attemps to change the name visibility or anything, so you need to disable it if you are using some citizens addon that changes their name", "  #needs relog to see the change"));
		configComments.put("per-world-settings:", Lists.newArrayList("", "#list of worlds with different settings than default, other worlds will use settings from above"));
		configComments.put("per-server-settings:", Lists.newArrayList("", "#list of servers with different settings than default, other servers will use settings from above"));
		configComments.put("placeholders:", Lists.newArrayList("#setting output of some placeholders"));
		configComments.put("  time-offset:", Lists.newArrayList("  #if time doesn't show correctly, you can change the time it shows", "  #setting to -1 will make it show 1 hour less than it does currently, 1 makes it show one hour more than currently, 0 does nothing"));
		configComments.put("  remove-strings:", Lists.newArrayList("  #remove these strings from everywhere. Typically an empty output when using some clan/faction related plugin but player isn't in any", "  #so players won't have empty brackets before their names"));
		configComments.put("rank-aliases:", Lists.newArrayList("", "#better showing of %rank% placeholder"));
		configComments.put("disable-features", Lists.newArrayList(""));
		configComments.put("belowname:", Lists.newArrayList("", "#the vanilla belowname feature", "#doesn't support player placeholders (faction, prefix), only server placeholders (time, date, animations) and static text"));
		bossbarComments = new HashMap<String, List<String>>();
		bossbarComments.put("bossbar-enabled:", Lists.newArrayList("#styles (SERVER 1.9+)", "#NOTCHED_6, NOTCHED_10, NOTCHED_12, NOTCHED_20, PROGRESS", "", "#colors (SERVER 1.9+)", "#BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW", "", "#IF YOU ARE USING SERVER 1.8.X", "#the entity will be slightly visible when progress is less than ~50% (client-sided bug)", "#only 1 line can be displayed at a time", "", "#you can also announce a message using /tab announce bar <bar name> <duration in seconds> (does not support animations yet)", ""));
		bossbarComments.put("default-bars:", Lists.newArrayList("#to have no default bars, set it to ", "#default-bars: []"));
		animationComments = new HashMap<String, List<String>>();
		animationComments.put("animations:", Lists.newArrayList("#usage: %animation:NAME%  or  {animation:NAME}"));
		advancedconfigComments = new HashMap<String, List<String>>();
		advancedconfigComments.put("per-world-playerlist:", Lists.newArrayList("#players will only see in tablist those who are in the same world"));
		advancedconfigComments.put("allow-pwp-bypass-permission:", Lists.newArrayList("", "#allow players with tab.bypass permission to see every player in tablist even if the setting above is enabled"));
		advancedconfigComments.put("ignore-pwp-in-worlds:", Lists.newArrayList("", "#even if per-world-playerlist is enabled, people in these worlds will see everyone on server in tablist"));
		advancedconfigComments.put("sort-players-by-permissions:", Lists.newArrayList("", "#sorting by permissions. Permission node is \"tab.sort.<group>\" and priorities can be set in config"));
		advancedconfigComments.put("fix-pet-names:", Lists.newArrayList("", "#an option to remove minecraft vanilla feature since 1.9 making named pets have same prefix as their owner (and being invisible when unlimited nametag mode is enabled)", "#needs relog to see the change"));
		advancedconfigComments.put("use-primary-group:", Lists.newArrayList("", "#asking permission plugin for primary group only"));
		advancedconfigComments.put("primary-group-finding-list:", Lists.newArrayList("", "#if the option above is disabled, full group list will be asked for and group higher in this list will be used as primary"));
	}
	public static void loadFiles() throws Exception {
		if (errorFile.exists()) {
			if (errorFile.length() > 10000000) {
				errorFile.delete();
			}
		}
		loadConfig();
		loadAnimations();
		loadBossbar();
		loadTranslation();
		if (Premium.is()) Premium.loadPremiumConfig();
	}

	public static void loadConfig() throws Exception {
		Shared.mainClass.loadConfig();
		if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 8) {
			HeaderFooter.enable = config.getBoolean("enable-header-footer", true);
			Playerlist.enable = config.getBoolean("change-tablist-prefix-suffix", true);
		}
		collision = config.getBoolean("enable-collision", true);
		timeFormat = config.getString("placeholders.time-format", "[HH:mm:ss / h:mm a]");
		timeOffset = config.getDouble("placeholders.time-offset", 0);
		dateFormat = config.getString("placeholders.date-format", "dd.MM.yyyy");
		doNotMoveSpectators = config.getBoolean("do-not-move-spectators", false);
		sortedGroups = new LinkedHashMap<String, String>();
		int index = 1;
		for (Object group : config.getList("group-sorting-priority-list", Lists.newArrayList("Owner", "Admin", "Mod", "Helper", "Builder", "Premium", "Player", "default"))){
			String sort = index+"";
			while (sort.length()<4) {
				sort = "0" + sort;
			}
			sortedGroups.put(group+"", sort);
			index++;
		}
		Map<String, Object> cs = config.getConfigurationSection("rank-aliases");
		if (cs != null) {
			rankAliases = cs;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Admin", "&4&lADMIN");
			map.put("Mod", "&b&lMOD");
			map.put("Premium", "&6&lPREMIUM");
			map.put("Ultra", "&b&lULTRA");
			map.put("Legend", "&a&lLEGEND");
			map.put("Titan", "&c&lTITAN");
			map.put("Youtuber", "&c&lYOUTUBE");
			map.put("_OTHER_", "%vault-prefix%");
			config.set("rank-aliases", rankAliases = map);
			config.save();
		}
		disabledHeaderFooter = config.getList("disable-features-in-"+Shared.mainClass.getSeparatorType()+"s.header-footer", Lists.newArrayList("disabled" + Shared.mainClass.getSeparatorType()));
		disabledTablistNames = config.getList("disable-features-in-"+Shared.mainClass.getSeparatorType()+"s.tablist-names", Lists.newArrayList("disabled" + Shared.mainClass.getSeparatorType()));
		disabledNametag = config.getList("disable-features-in-"+Shared.mainClass.getSeparatorType()+"s.nametag", Lists.newArrayList("disabled" + Shared.mainClass.getSeparatorType()));
		disabledTablistObjective = config.getList("disable-features-in-"+Shared.mainClass.getSeparatorType()+"s.tablist-objective", Lists.newArrayList("disabled" + Shared.mainClass.getSeparatorType()));
		disabledBossbar = config.getList("disable-features-in-"+Shared.mainClass.getSeparatorType()+"s.bossbar", Lists.newArrayList("disabled" + Shared.mainClass.getSeparatorType()));
		disabledBelowname = config.getList("disable-features-in-"+Shared.mainClass.getSeparatorType()+"s.belowname", Lists.newArrayList("disabled" + Shared.mainClass.getSeparatorType()));
	}
	public static void loadAnimations() throws Exception {
		animation = new ConfigurationFile("animations.yml", animationComments);
		animations = new ArrayList<Animation>();
		if (animation.getConfigurationSection("animations") != null) {
			for (String s : animation.getConfigurationSection("animations").keySet())
				animations.add(new Animation(s, animation.getList("animations." + s + ".texts"), animation.getInt("animations." + s + ".change-interval", 1000)));
		}
	}
	@SuppressWarnings("unchecked")
	public static void loadBossbar() throws Exception {
		bossbar = new ConfigurationFile("bossbar.yml", bossbarComments);
		if (bossbar.get("enabled") != null) {
			Shared.startupWarn("You are using old bossbar config, please make a backup of the file and delete it to get new file.");
			return;
		}
		BossBar.enabled = bossbar.getBoolean("bossbar-enabled", false);
		BossBar.refresh = bossbar.getInt("refresh-interval-milliseconds", 1000);
		BossBar.toggleCommand = bossbar.getString("bossbar-toggle-command", "/bossbar");
		BossBar.defaultBars = bossbar.getStringList("default-bars");
		BossBar.perWorld = (Map<String, List<String>>) bossbar.get("per-world");
		if (BossBar.perWorld == null) BossBar.perWorld = Maps.newConcurrentMap();
		BossBar.lines.clear();
		if (bossbar.getConfigurationSection("bars") != null) {
			for (String bar : bossbar.getConfigurationSection("bars").keySet()){
				boolean permissionRequired = bossbar.getBoolean("bars." + bar + ".permission-required");
				int refresh = bossbar.getInt("bars." + bar + ".refresh");
				String style = bossbar.getString("bars." + bar + ".style");
				String color = bossbar.getString("bars." + bar + ".color");
				String progress = bossbar.getString("bars." + bar + ".progress");
				String text = bossbar.getString("bars." + bar + ".text");
				BossBar.lines.add(new BossBarLine(bar, permissionRequired, refresh, color, style, text, progress));
			}
		}
	}
	public static void loadTranslation() throws Exception {
		translation = new ConfigurationFile("translation.yml", Maps.newHashMap());
		no_perm = translation.getString("no_permission", "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.").replace("&", "�");
		unlimited_nametag_mode_not_enabled = translation.getString("unlimited_nametag_mode_not_enabled", "&c[TAB] Warning! To make these work, you need to enable unlimited-nametag-prefix-suffix-mode in config !").replace("&", "�");
		data_removed = translation.getString("data_removed", "&3[TAB] All data has been successfully removed from %category% �e%value%").replace("&", "�");
		player_not_found = translation.getString("player_not_found", "&4[TAB] Player not found !").replace("&", "�");
		reloaded = translation.getString("reloaded", "&3[TAB] Reloaded").replace("&", "�");
		value_assigned = translation.getString("value_assigned", "&3[TAB] %type% &r'%value%'&r&3 has been successfully assigned to %category% &e%unit%").replace("&", "�");
		value_removed = translation.getString("value_removed", "&3[TAB] %type% has been successfully removed from %category% &e%unit%").replace("&", "�");
		help_menu = translation.getList("help_menu");
		bossbar_on = translation.getString("bossbar-toggle-on", "&2Bossbar is now visible").replace("&", "�");
		bossbar_off = translation.getString("bossbar-toggle-off", "&7Bossbar is no longer visible. Magic!").replace("&", "�");
		preview_on = translation.getString("preview-on", "&7Preview mode &aactivated.").replace("&", "�");
		preview_off = translation.getString("preview-off", "&7Preview mode &3deactivated.").replace("&", "�");
	}
}