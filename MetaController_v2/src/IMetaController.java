

public interface IMetaController {
	
	void processRequest(int destinationFloorNumber, Direction direction);
	
	void registerCar(ICar car);
	
	void setAlgorithm(IAlgorithm algorithm);
		
	void unregisterCar(ICar car);

}
