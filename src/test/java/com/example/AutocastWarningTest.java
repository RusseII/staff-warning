package com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AutocastWarningTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(AutocastWarning.class);
		RuneLite.main(args);
	}
}