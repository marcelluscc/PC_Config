package actions;

import java.io.IOException;

public class Volume implements ActionInterface {

	@Override
	public void activated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub

	}
	
	public Volume(int newVolume){
		newVolume = newVolume > 50 ? 50:newVolume;
		newVolume = newVolume < 0 ? 0:newVolume;
		String command = "nircmd.exe setsysvolume " + (65535 / 100 * newVolume);
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
