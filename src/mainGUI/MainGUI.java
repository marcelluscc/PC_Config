package mainGUI;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import teleBot.TelegramBot;

public class MainGUI {
	public static void main(String[] args) {
		activateBot();
	}
	
	private static void activateBot(){
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new TelegramBot());
		} catch (TelegramApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
