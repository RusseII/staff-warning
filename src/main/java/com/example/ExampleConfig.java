package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config {
	@ConfigItem(keyName = "staff bash", name = "Welcome Greeting", description = "Gives a warning when you will attack with staff")
	default String greeting() {
		return "Hello";
	}
}
