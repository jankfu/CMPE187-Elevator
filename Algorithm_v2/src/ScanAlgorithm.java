import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Anshu
 * 
 */
public class ScanAlgorithm implements IAlgorithm {

	@Override
	public ICar findBestCar(List<ICar> lstCars, Direction direction,
			int destinationFloorNumber) {

		ICar bestCar = null;

		if (lstCars.size() == 1) {
			bestCar = lstCars.get(0);
			return bestCar;
		}

		lstCars = partitionCars(lstCars, destinationFloorNumber);

		for (int i = 0; i < lstCars.size(); i++) {

			ICar car = lstCars.get(i);

			if ((direction == Direction.UP)
					& ((car.getStatus() == CarStatus.MOVING_UP) || ((car
							.getStatus() == CarStatus.STOPPED) & (car
							.getUserPanelQueue().isRequestUp() == 1)))
					& (car.getCurrentFloorNumber() <= destinationFloorNumber)) {
				bestCar = lstCars.get(i);
				break;
			} else if ((direction == Direction.DOWN)
					& ((car.getStatus() == CarStatus.MOVING_DOWN) || ((car
							.getStatus() == CarStatus.STOPPED) & (car
							.getUserPanelQueue().isRequestDown() == 1)))
					& (car.getCurrentFloorNumber() >= destinationFloorNumber)) {
				bestCar = lstCars.get(i);
				break;
			} else if (car.getStatus() == CarStatus.IDLE) {
				bestCar = lstCars.get(i);
			}
		}
		if (bestCar == null) {
			//No best car found, so assigns random car
			Random generator = new Random();
			int randomInt = generator.nextInt(lstCars.size());
			bestCar = lstCars.get(randomInt);
		}
		return bestCar;
	}

	private List<ICar> partitionCars(List<ICar> lstCars,
			int destinationFloorNumber) {

		ArrayList<ICar> oddCars = new ArrayList<ICar>();
		ArrayList<ICar> evenCars = new ArrayList<ICar>();

		for (int i = 0; i < lstCars.size(); i++) {
			ICar car1 = lstCars.get(i);
			IUserPanel us = car1.getUserPanel();
			if (us.getSelection() == 1)
				evenCars.add(car1);
			else if (us.getSelection() == 2)
				oddCars.add(car1);
			else {
				oddCars.add(car1);
				evenCars.add(car1);
			}

		}

		if (destinationFloorNumber % 2 == 0)
			return evenCars;
		else
			return oddCars;

	}

	public static void main(String args[]) {
		ICar car1 = new Car();
		IUserPanel u1 = new UserPanel();

		u1.setSelection(1);

		ICarController carController1 = new CarController();
		IUserPanelQueue q1 = new UserPanelQueue();
		q1.setCar(car1);

		car1.setCurrentFloorNumber(3);
		car1.setUserPanelQueue(q1);
		car1.setCarController(carController1);
		car1.setUserPanel(u1);

		u1.setCar(car1);
		carController1.setCar(car1);
		car1.setStatus(CarStatus.IDLE);

		List<ICar> a = new ArrayList<ICar>();

		a.add(car1);

		IAlgorithm al = new ScanAlgorithm();

		al.findBestCar(a, Direction.UP, 1);

	}

}
