package actions;

import java.io.IOException;

public class ShutDown implements ActionInterface{
	int minuteTime=0;
	public ShutDown(){
		new ShutDown(0);
	}
	
	public ShutDown(int minuteTime){
		this.minuteTime=minuteTime;
	}

	@Override
	public void cancelled(){
		String shutdownCommand = "shutdown /a";
		try {
			Runtime.getRuntime().exec(shutdownCommand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void activated() {
		String shutdownCommand = "shutdown -s -f -t " + this.minuteTime*60;
		try {
			Runtime.getRuntime().exec(shutdownCommand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
