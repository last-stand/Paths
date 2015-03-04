import java.util.*;

public class FindPath extends Paths{
	static List<String> flightPath = new ArrayList<String>();
	
	public static List<String> pathFinder(String source,String destination){
		flightPath.add(source);
		if(isDirectFlight(source,destination)){
		 	flightPath.add(destination);
		}
		return flightPath;
	}
}