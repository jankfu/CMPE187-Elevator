import javax.swing.JPanel;


public interface IDoor {
	
	void closeDoor();
	
	void openDoor();
		
	void setCarController(ICarController carController);
	
	JPanel createDoorUI();
	
	void setDoorStatus(String doorStatus);
	
	String getDoorStatus();

}
