package me.neznamy.tab.api;

import java.util.*;

import me.neznamy.tab.shared.*;
import me.neznamy.tab.shared.packets.PacketPlayOutPlayerListHeaderFooter;

public class TABAPI {

	public static List<UUID> hiddenNametag = new ArrayList<UUID>();
	
	
	/**
	 * Returns true if enabled, false if disabled
	 * @return Whether unlimited nametag mode is enabled or not
	 * @see enableUnlimitedNameTagModePermanently
	 * @since 2.4.12
	 */
	public static boolean isUnlimitedNameTagModeEnabled() {
		return Configs.unlimitedTags;
	}
	
	
	/**
	 * Enables unlimited nametag mode permanently in config
	 * @throws IllegalStateException if called from a proxy
	 * @see isUnlimitedNameTagModeEnabled
	 * @since 2.4.12
	 */
	public static void enableUnlimitedNameTagModePermanently() {
		if (Shared.mainClass instanceof me.neznamy.tab.platforms.bukkit.Main) {
			Configs.config.set("change-nametag-prefix-suffix", true);
			Configs.config.set("unlimited-nametag-prefix-suffix-mode.enabled", true);
			Configs.config.save();
			me.neznamy.tab.platforms.bukkit.Main.instance.unload();
			me.neznamy.tab.platforms.bukkit.Main.instance.load(false, false);
		} else throw new IllegalStateException();
	}
	
	
	/**
	 * Changes the requested property of a player temporarily (until next restart, reload or /tab reload)
	 * @param player UUID of player
	 * @param type Type of property
	 * @param value The value to be used
	 * @since 2.5.3
	 */
	public static void setValueTemporarily(UUID player, EnumProperty type, String value) {
		ITabPlayer p = Shared.getPlayer(player);
		if (p == null) return;
		p.properties.get(type.toString()).setTemporaryValue(value);
		p.forceUpdateDisplay();
	}
	
	
	/**
	 * Changes the requested property of a player permanently (saved into config too)
	 * @param player UUID of player
	 * @param type Type of property
	 * @param value The value to be used
	 * @since 2.5.3
	 */
	public static void setValuePermanently(UUID player, EnumProperty type, String value) {
		ITabPlayer p = Shared.getPlayer(player);
		if (p == null) return;
		p.properties.get(type.toString()).changeRawValue(value);
		TabCommand.savePlayer(null, p.getName(), type.toString(), value);
		p.forceUpdateDisplay();
	}
	
	
	/**
	 * Returns temporary value of player's property or null if not set
	 * @param player UUID of player
	 * @param type Type of property
	 * @return Temporary value of player's property or null if not set
	 * @see hasTemporaryValue
	 * @see setValueTemporarily
	 * @since 2.5.3
	 */
	public static String getTemporaryValue(UUID player, EnumProperty type) {
		return Shared.getPlayer(player).properties.get(type.toString()).getTemporaryValue();
	}
	
	
	/**
	 * Returns Whether player has temporary value or not
	 * @param player UUID of player
	 * @param type Type of property
	 * @return Whether player has temporary value or not
	 * @since 2.5.3
	 */
	public static boolean hasTemporaryValue(UUID player, EnumProperty type) {
		return getTemporaryValue(player, type) != null;
	}
	
	
	/**
	 * Removes temporary value from player if set
	 * @param player UUID of player
	 * @param type Type of property
	 * @since 2.5.3
	 */
	public static void removeTemporaryValue(UUID player, EnumProperty type) {
		setValueTemporarily(player, type, null);
	}
	
	
	/**
	 * Returns original value of property of player
	 * @param player UUID of player
	 * @param type Type of property
	 * @return Original value of property of player
	 * @since 2.5.3
	 */
	public static String getOriginalValue(UUID player, EnumProperty type) {
		return Shared.getPlayer(player).properties.get(type.toString()).getOriginalRawValue();
	}
	
	
	/**
	 * Sends requested header and footer to player
	 * @param player UUID of player
	 * @param header Header
	 * @param footer Footer
	 * @since 2.4.12
	 */
	public static void sendHeaderFooter(UUID player, String header, String footer) {
		Shared.getPlayer(player).sendCustomPacket(new PacketPlayOutPlayerListHeaderFooter(header, footer));
	}
	
	
	/**
	 * Sends empty header/footer to a player
	 * @param player UUID of player
	 * @since 2.4.12
	 */
	public static void clearHeaderFooter(UUID player) {
		sendHeaderFooter(player, "", "");
	}
	
	
	/**
	 * Makes player's nametag invisible until server restart/reload or /plugman reload tab
	 * @param player UUID of player
	 * @see showNametag 
	 * @see hasHiddenNametag
	 * @since 2.4.12
	 */
	public static void hideNametag(UUID player) {
		hiddenNametag.add(player);
		Shared.getPlayer(player).updateTeamData();
	}
	
	
	/**
	 * Makes player's nametag visible again
	 * @param player UUID of player
	 * @see hideNametag
	 * @see hasHiddenNametag
	 * @since 2.4.12
	 */
	public static void showNametag(UUID player) {
		hiddenNametag.remove(player);
		Shared.getPlayer(player).updateTeamData();
	}
	
	
	/**
	 * Return whether player has hidden nametag or not
	 * @param player UUID of player
	 * @return Whether player has hidden nametag or not
	 * @since 2.4.12
	 * @see hideNametag
	 * @see showNametag
	 */
	public static boolean hasHiddenNametag(UUID player) {
		return hiddenNametag.contains(player);
	}
	
	@Deprecated
	public static void setCustomTabNameTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.CUSTOMTABNAME, value);
	}
	@Deprecated
	public static void setCustomTagNameTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.CUSTOMTAGNAME, value);
	}
	@Deprecated
	public static void setTabPrefixTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.TABPREFIX, value);
	}
	@Deprecated
	public static void setTabSuffixTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.TABSUFFIX, value);
	}
	@Deprecated
	public static void setTagPrefixTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.TAGPREFIX, value);
	}
	@Deprecated
	public static void setTagSuffixTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.TAGSUFFIX, value);
	}
	@Deprecated
	public static void setAboveNameTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.ABOVENAME, value);
	}
	@Deprecated
	public static void setBelowNameTemporarily(UUID uniqueId, String value) {
		setValueTemporarily(uniqueId, EnumProperty.BELOWNAME, value);
	}
	
	@Deprecated
	public static void setCustomTabNamePermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.CUSTOMTABNAME, value);
	}
	@Deprecated
	public static void setCustomTagNamePermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.CUSTOMTAGNAME, value);
	}
	@Deprecated
	public static void setTabPrefixPermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.TABPREFIX, value);
	}
	@Deprecated
	public static void setTabSuffixPermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.TABSUFFIX, value);
	}
	@Deprecated
	public static void setTagPrefixPermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.TAGPREFIX, value);
	}
	@Deprecated
	public static void setTagSuffixPermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.TAGSUFFIX, value);
	}
	@Deprecated
	public static void setAboveNamePermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.ABOVENAME, value);
	}
	@Deprecated
	public static void setBelowNamePermanently(UUID uniqueId, String value) {
		setValuePermanently(uniqueId, EnumProperty.BELOWNAME, value);
	}
	
	@Deprecated
	public static String getTemporaryCustomTabName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtabname").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryCustomTagName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtagname").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryTabPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabprefix").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryTabSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabsuffix").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryTagPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagprefix").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryTagSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagsuffix").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryAboveName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("abovename").getTemporaryValue();
	}
	@Deprecated
	public static String getTemporaryBelowName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("belowname").getTemporaryValue();
	}
	
	@Deprecated
	public static boolean hasTemporaryCustomTabName(UUID uniqueId) {
		return getTemporaryCustomTabName(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryCustomTagName(UUID uniqueId) {
		return getTemporaryCustomTagName(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryTabPrefix(UUID uniqueId) {
		return getTemporaryTabPrefix(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryTabSuffix(UUID uniqueId) {
		return getTemporaryTabSuffix(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryTagPrefix(UUID uniqueId) {
		return getTemporaryTagPrefix(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryTagSuffix(UUID uniqueId) {
		return getTemporaryTagSuffix(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryAboveName(UUID uniqueId) {
		return getTemporaryAboveName(uniqueId) != null;
	}
	@Deprecated
	public static boolean hasTemporaryBelowName(UUID uniqueId) {
		return getTemporaryBelowName(uniqueId) != null;
	}
	
	@Deprecated
	public static void removeTemporaryCustomTabName(UUID uniqueId) {
		setCustomTabNameTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryCustomTagName(UUID uniqueId) {
		setCustomTagNameTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryTabPrefix(UUID uniqueId) {
		setTabPrefixTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryTabSuffix(UUID uniqueId) {
		setTabSuffixTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryTagPrefix(UUID uniqueId) {
		setTagPrefixTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryTagSuffix(UUID uniqueId) {
		setTagSuffixTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryAboveName(UUID uniqueId) {
		setAboveNameTemporarily(uniqueId, null);
	}
	@Deprecated
	public static void removeTemporaryBelowName(UUID uniqueId) {
		setBelowNameTemporarily(uniqueId, null);
	}
	
	@Deprecated
	public static String getOriginalCustomTabName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtabname").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalCustomTagName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtagname").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalTabPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabprefix").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalTagPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagprefix").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalTabSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabsuffix").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalTagSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagsuffix").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalAboveName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("abovename").getOriginalRawValue();
	}
	@Deprecated
	public static String getOriginalBelowName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("belowname").getOriginalRawValue();
	}
	
}