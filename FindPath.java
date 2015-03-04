import java.util.*;

public class FindPath extends Paths{
	static List<String> flightPath = new ArrayList<String>();

	public static List<String> pathFinder(String source,String destination){
		flightPath.add(source);
		if(isDirectFlight(source,destination))
		 	flightPath.add(destination);
		return flightPath;
	}

	public static String getPath(List<String> path){
		String pathStr = "";
		for(String str : path)
			pathStr += str + "->";
		return pathStr.substring(0,pathStr.length()-2);
	}
}