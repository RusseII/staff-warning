package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface AutocastWarningConfig extends Config {
	@ConfigItem(keyName = "Autocast Warning", name = "Autocast Warning", description = "Gives a warning when your magic level is too low to attack with blood barrage")
	default String greeting() {
		return "Hello";
	}
}
