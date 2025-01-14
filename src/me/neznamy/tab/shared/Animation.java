package me.neznamy.tab.shared;

import java.util.List;

public class Animation {
	
	private String name;
	private String[] messages;
	private int interval;
	
	public Animation(String name, List<Object> list, int interval){
		if (interval == 0) {
			Shared.startupWarn("Animation \"" + name + "\" has refresh interval of 0 milliseconds! Did you forget to configure it? Using 1000 to avoid issues.");
			interval = 1000;
		}
		if (interval % 50 > 0) {
			int oldInterval = interval;
			interval -= interval%50;
			if (interval == 0) interval = 50;
			Shared.startupWarn("Animation \"" + name + "\" has a refresh interval of �e" + oldInterval + "ms�c which is not divisible by 50! Animations can't refresh faster than every tick ( = 50 milliseconds). �bUsing " + interval + " for now.");
		}
		this.name = name;
		this.messages = list.toArray(new String[0]);
		this.interval = interval;
	}
	public String[] getAllMessages() {
		return messages;
	}
	public String getMessage(){
		return messages[(int) ((System.currentTimeMillis()%(messages.length*interval))/interval)];
	}
	public String getName(){
		return name;
	}
}