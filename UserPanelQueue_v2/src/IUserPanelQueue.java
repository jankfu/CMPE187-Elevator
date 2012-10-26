public interface IUserPanelQueue {

	void putMessage(int destinationFloorNumber);

	int getNumTasks();

	int pathLength(Direction direction, int destinationFloorNumber);

	void setCar(ICar car);

	void flushQueue();

	int isRequestUp();

	int isRequestDown();

}
