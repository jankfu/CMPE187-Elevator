
public class DoorFactory {
	
	public static IDoor createDoor(){
		IDoor door = null;
		
		if(DoorConfiguration.getDoorType().equalsIgnoreCase("SingleDoor")){
			door = new SingleDoor();
		}else if(DoorConfiguration.getDoorType().equalsIgnoreCase("DoubleDoor")){
			door = new DoubleDoor();
		}
		
		return door;
	}

}
