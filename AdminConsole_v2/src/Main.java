import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	private final static int NUMBER_OF_FLOORS = 8;
	private final static int NUMBER_OF_CARS = 5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());

		//Set configurations:
		FloorPanelConfiguration.setButtonColor(FloorPanelColor.RED);
		UserPanelConfiguration.setActiveButtonColor(UserPanelColor.YELLOW);
		UserPanelConfiguration.setButtonColor(UserPanelColor.BLUE);

		//Init floor panel
		JPanel completeFloorPanel = new JPanel();
		completeFloorPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int centerrow = 0;
		gbc.gridx = 0;
		gbc.gridy = centerrow;

		IMetaController metaController = MetaControllerFactory
				.getMetaControllerInstance();

		IFloorPanelQueue floorPanelQueue = new FloorPanelQueue();

		floorPanelQueue.setMetaContoller(metaController);

		FloorPanelConfiguration.setTextType("text");

		IFloorPanel ifloorPanel = null;

		gbc.gridy = centerrow++;

		for (int j = NUMBER_OF_FLOORS; j >= 1; j--) {
			ifloorPanel = FloorPanelFactory.createFloorPanel();
			JPanel panel = ifloorPanel.createFloorPanel(j);
			if (j == NUMBER_OF_FLOORS)
				ifloorPanel.disableUpButton();
			if (j == 1) {
				ifloorPanel.disableDownButton();
			}

			ifloorPanel.setFloorPanelQueueType(floorPanelQueue);
			completeFloorPanel.add(panel, gbc);
			gbc.gridy = centerrow++;
		}
		frame.add(completeFloorPanel);

		for (int i = 0; i < NUMBER_OF_CARS; i++) {
			ICar car = CarFactory.getCarInstance();

			IUserPanel userPanel = UserPanelFactory.getUserPanelInstance();

			IUserPanelQueue userPanelQueue = new UserPanelQueue();
			ICarController carController = new CarController();
			IDoorPanel doorPanel = DoorPanelFactory.createDoorPanel();
			IDoor door = DoorFactory.createDoor();

			car.setUserPanel(userPanel);
			car.setUserPanelQueue(userPanelQueue);
			car.setCarController(carController);
			car.setDoor(door);
			car.setDoorPanel(doorPanel);

			userPanel.setCar(car);
			userPanel.setNumberFloors(NUMBER_OF_FLOORS);

			userPanelQueue.setCar(car);
			carController.setCar(car);
			carController.setFloorPanel(ifloorPanel);

			doorPanel.setCar(car);

			metaController.registerCar(car);

			frame.add(car.createCar());
		}
		
		
		frame.setSize(300 * NUMBER_OF_CARS, NUMBER_OF_FLOORS * 80);
		frame.setVisible(true);

	}

}
