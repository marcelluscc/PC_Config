package actions;

import java.io.IOException;

public class MonitorOff implements ActionInterface{

	@Override
	public void activated() {
		String monitorOffCommand = "nircmd monitor off";
		try {
			Runtime.getRuntime().exec(monitorOffCommand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void cancelled() {
		String monitorOnCommand = "nircmd movecursor 100 100";
		try {
			Runtime.getRuntime().exec(monitorOnCommand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
