package teleBot;

import java.util.ArrayList;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import actions.MonitorOff;
import actions.Restart;
import actions.ShutDown;
import actions.Volume;

public class TelegramBot extends TelegramLongPollingBot{

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "StockMonitoringBot";
	}

	@Override
	public void onUpdateReceived(Update arg0) {
		try {
			handleMessage(arg0);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleMessage(Update arg0) throws TelegramApiException {
		SendMessage message = new SendMessage().setChatId(arg0.getMessage().getChatId());
		if(!isFromRegisteredUser(arg0.getMessage().getChatId())){
			message.setText("This bot is for private use only. Please acquire access..");
			sendMessage(message);
			return;
		}
		if(isTimeout(arg0)) {
			message.setText("Connection Timeout. Please try again");
			sendMessage(message);
			return;
		}

		
		if(arg0.hasMessage()){
			String command = arg0.getMessage().getText();
			ArrayList<String> commandArr = new ArrayList<String>();
			for (String comm : command.split(" ")){
				commandArr.add(comm);
			}
			if (commandArr.get(0).equalsIgnoreCase("/shutdown")){
				if(commandArr.size()>1){
					try{
						Integer minuteTime = Integer.parseInt(commandArr.get(1));
						message.setText("Marcel-pc will shutdown in " + minuteTime + " minutes.");
						sendMessage(message);
						new ShutDown(minuteTime).activated();
					}
					catch (NumberFormatException e){
						message.setText("shutdown command is not valid. Type the number of minutes after 'shutdown'");
						sendMessage(message);
					}
				}
				else{
					message.setText("Marcel-pc will shutdown now");
					sendMessage(message);
					new ShutDown().activated();
				}
			}
			else if (commandArr.get(0).equalsIgnoreCase("/restart")){
				if(commandArr.size()>1){
					try{
						Integer minuteTime = Integer.parseInt(commandArr.get(1));
						message.setText("Marcel-pc will be restarted in " + minuteTime + " minutes.");
						sendMessage(message);
						new Restart(minuteTime).activated();
					}
					catch (NumberFormatException e){
						message.setText("restart command is not valid. Type the number of minutes after 'restart'");
						sendMessage(message);
					}
				}
				else{
					message.setText("Marcel-pc will be restarted now");
					sendMessage(message);
					new Restart().activated();
				}
			}
			else if (commandArr.get(0).equalsIgnoreCase("/shutdown_cancel")){
				new ShutDown().cancelled();
				message.setText("Shutdown / Restart cancelled");
				sendMessage(message);
			}
			else if (commandArr.get(0).equalsIgnoreCase("/monitor_off")){
				new MonitorOff().activated();
				message.setText("Monitor has been turned off");
				sendMessage(message);
			}
			else if (commandArr.get(0).equalsIgnoreCase("/monitor_on")){
				new MonitorOff().cancelled();
				message.setText("Monitor has been turned on");
				sendMessage(message);
			}
			else if (commandArr.get(0).equalsIgnoreCase("/volume")){
				if(commandArr.size()>1){
					Integer newVolume = Integer.parseInt(commandArr.get(1));
					message.setText("Volume has been set to " + newVolume + " .");
					sendMessage(message);
					new Volume(newVolume);
				}
				else{
					message.setText("please type /volume NewVolume, e.g. /volume 40");
					sendMessage(message);
				}
			}
			else {
				message.setText("type / for list of commands");
				sendMessage(message);
			}
		}
		else {
			message.setText("Type /help to list down all commands");
			sendMessage(message);
		}
	}

	private boolean isFromRegisteredUser(Long chatId) {
		if (chatId==285859126) return true;
		return false;
	}

	private boolean isTimeout(Update arg0){
		long currentTimeInSec = System.currentTimeMillis()/1000;
		long messageTimeInSec = arg0.getMessage().getDate();
		//Timeout if message was received 2 minutes ago
		if(Math.abs(messageTimeInSec - currentTimeInSec) > 120) return true;
		return false;
	}
	
	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "289491347:AAE49_UBbpIpUQY83Czg35KNwj27nD-bVps";
	}
	
	public TelegramBot(){
		SendMessage message = new SendMessage().setChatId((long) 285859126);
		message.setText("Marcel-PC has been activated");
		try {
			sendMessage(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/*
	 * list of commands
	 * shutdown - shutdown the pc in minutes time
	 * restart - restart the pc in minutes time
	 * shutdown_cancel - cancel the shutdown
	 * monitor_off - turn off monitor
	 * monitor_on - turn on monitor
	 * volume - set pc-volume with range 0 - 100
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
}
