package com.ZamanGames.RabbitGame.desktop;

import com.ZamanGames.RabbitGame.game.RabbitGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ZamanGames.RabbitGame.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new RabbitGame(), config);
	}
}
