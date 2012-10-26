import java.util.ArrayList;
import java.util.List;


public class ShortestPathAlgorithm implements IAlgorithm {

	@Override
	public ICar findBestCar(List<ICar> lstCars, Direction direction, int destinationFloorNumber) {
		
		ICar bestCar = null;
		int pathLength = 0;
		
		
		if(lstCars.size() ==1){
			bestCar = lstCars.get(0);
			return bestCar;
		}
		
		lstCars =partitionCars(lstCars,destinationFloorNumber);
							
		pathLength = lstCars.get(0).getUserPanelQueue().pathLength(direction, destinationFloorNumber);
		bestCar = lstCars.get(0);
		
		for(int i=1; i< lstCars.size(); i++){			
			
			ICar car = lstCars.get(i);			
			if(pathLength < car.getUserPanelQueue().pathLength(direction, destinationFloorNumber)){	 
				
			}else{
				pathLength = car.getUserPanelQueue().pathLength(direction, destinationFloorNumber);
				bestCar = lstCars.get(i); 
			}
		}
		return bestCar;
	}
	
	private List<ICar> partitionCars(List<ICar> lstCars, int destinationFloorNumber) {		
		ArrayList<ICar> oddCars=new ArrayList<ICar>();
		ArrayList<ICar> evenCars=new ArrayList<ICar>();
		
		for(int i=0;i<lstCars.size();i++){
				ICar car1=lstCars.get(i);
				IUserPanel us=car1.getUserPanel();
				if(us.getSelection()==1)
					evenCars.add(car1);
				else if(us.getSelection()==2)
					oddCars.add(car1);
				else{
					oddCars.add(car1);
					evenCars.add(car1);
				}
					
					
		}
		
		if (destinationFloorNumber%2==0)
					return evenCars;
		else
			return oddCars;
		
	}

	public static void main(String args[]){
		ICar car1 = new Car();
		IUserPanel u1=new UserPanel();
		
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
				
		List<ICar> a =  new ArrayList<ICar>();
		
		a.add(car1);
		
		IAlgorithm al = new ShortestPathAlgorithm();
		
		al.findBestCar(a, Direction.UP, 1);		
		
	}
	
	
}
