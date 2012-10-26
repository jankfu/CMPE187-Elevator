

public class MetaControllerFactory {
	
	public static IMetaController getMetaControllerInstance(){
		IMetaController metaController = null;
		
		if(MetaControllerConfiguration.getMetaControllerType().equalsIgnoreCase("default")){
			metaController = new MetaController();
		}
		
		if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("Shortest Path")){
			metaController.setAlgorithm(new ShortestPathAlgorithm());
		}else if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("Random")){
			metaController.setAlgorithm(new RandomAlgorithm());
		}
		
		else if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("SCAN")){
			metaController.setAlgorithm(new ScanAlgorithm());
		}		
		return metaController;
	}

}
