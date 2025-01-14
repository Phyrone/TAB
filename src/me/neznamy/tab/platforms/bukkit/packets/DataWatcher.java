package me.neznamy.tab.platforms.bukkit.packets;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.neznamy.tab.platforms.bukkit.packets.method.MethodAPI;
import me.neznamy.tab.shared.ProtocolVersion;

public class DataWatcher{

	private Object entity;
	private Map<Integer, Item> dataValues = new HashMap<Integer, Item>();

	public DataWatcher(Object entity) {
		this.entity = entity;
	}
	public void setValue(DataWatcherObject type, Object value){
		dataValues.put(type.getPosition(), new Item(type, value));
	}
	public Item getItem(int position) {
		return dataValues.get(position);
	}
	public List<Item> getObjectsThatNeedUpdate(){
		ArrayList<Item> arraylist = new ArrayList<Item>();
		for (Item object : dataValues.values()) {
			if (object.needsUpdate){
				object.needsUpdate = false;
				arraylist.add(object);
			}
		}
		if (arraylist.isEmpty()) arraylist = null;
		return arraylist;
	}
	public List<Item> getAllObjects(){
		ArrayList<Item> arraylist = new ArrayList<Item>();
		arraylist.addAll(dataValues.values());
		return arraylist;
	}

	public static class Item{

		public DataWatcherObject type;
		public Object value;
		public boolean needsUpdate;

		public Item(DataWatcherObject type, Object value){
			this.type = type;
			this.value = value;
			this.needsUpdate = true;
		}
		public Item setNeedsUpdate(boolean needsUpdate) {
			this.needsUpdate = needsUpdate;
			return this;
		}
		public Object toNMS(){
			return MethodAPI.getInstance().newDataWatcherItem(type, value, needsUpdate);
		}
		public static Item fromNMS(Object nmsObject){
			return MethodAPI.getInstance().readDataWatcherItem(nmsObject);
		}
	}
	public Object toNMS(){
		Object nmsWatcher = MethodAPI.getInstance().newDataWatcher(entity);
		for (Item item : dataValues.values()) MethodAPI.getInstance().DataWatcher_register(nmsWatcher, item.type, item.value);
		return nmsWatcher;
	}
	public static DataWatcher fromNMS(Object nmsWatcher) throws Exception{
		DataWatcher watcher = new DataWatcher(ENTITY.get(nmsWatcher));
		for (Object watchableObject : MethodAPI.getInstance().getDataWatcherItems(nmsWatcher)) {
			Item w = Item.fromNMS(watchableObject);
			watcher.setValue(w.type, w.value);
		}
		return watcher;
	}

	private static Field ENTITY;

	static {
		Map<String, Field> fields = PacketPlayOut.getFields(MethodAPI.DataWatcher);
		if (ProtocolVersion.SERVER_VERSION.getNetworkId() >= ProtocolVersion.v1_14_4.getNetworkId()) {
			//1.14.4+
			ENTITY = fields.get("entity");
		} else if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 10) {
			//1.10 - 1.14.3
			ENTITY = fields.get("c");
		} else if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 9) {
			//1.9.x
			ENTITY = fields.get("b");
		} else {
			//1.7.x - 1.8.x
			ENTITY = fields.get("a");
		}
	}
}