package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.StatChanged;
import net.runelite.api.Skill;
import net.runelite.client.Notifier;
import java.awt.Graphics2D;
import net.runelite.client.config.RuneLiteConfig;
import java.awt.Color;
import java.awt.Rectangle;
import net.runelite.api.MainBufferProvider;

@Slf4j
@PluginDescriptor(name = "Example")
public class ExamplePlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	@Inject
	private Notifier notifier;

	@Inject
	private RuneLiteConfig runeLiteConfig;

	private static Graphics2D lastGraphics;

	private static MainBufferProvider lastMainBufferProvider;

	@Override
	protected void startUp() throws Exception {
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example2 says " + config.greeting(), null);
		}
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ExampleConfig.class);
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
		Skill skill = statChanged.getSkill();

		if (!skill.equals(Skill.MAGIC)) {
			return;
		}

		int level = client.getBoostedSkillLevel(skill);
		if (level < 92) {
			// notifier.notify("test");
			MainBufferProvider bufferProvider = (MainBufferProvider) client.getBufferProvider();

			Graphics2D graphics2d = (Graphics2D) bufferProvider.getImage().getGraphics();

			// notifier.processFlash(graphics2d);
			// final Color color = graphics2d.getColor();
			graphics2d.setColor(runeLiteConfig.notificationFlashColor());
			graphics2d.fill(new Rectangle(client.getCanvas().getSize()));
			// graphics2d.setColor(color);
		}
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example6 says " + level, null);

	}

}
