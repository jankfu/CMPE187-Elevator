import javax.swing.JLabel;
import javax.swing.JPanel;


public class TestDoor implements IDoor {

	@Override
	public void closeDoor() {

	}

	@Override
	public JPanel createDoorUI() {
		JPanel j = new JPanel();
		j.add(new JLabel("This is test door"));
		return j;
	}

	@Override
	public String getDoorStatus() {
		return null;
	}

	@Override
	public void openDoor() {

	}

	@Override
	public void setCarController(ICarController arg0) {

	}

	@Override
	public void setDoorStatus(String arg0) {
	}

}
