import java.util.*;

public class FindPath extends Paths{
	Queue<String> flightPath = new LinkedList<String>();
	static String source, destination;
	FindPath(String source, String destination){
		this.source = source;
		this.destination = destination;
	}
	public int pathFinder(){
		flightPath.add(source);
		if(isDirectFlight(source,destination)){
		 	flightPath.add(destination);
		 	return 1;
		}
		return 0;
	}

	public static String getPath(Queue<String> path){
		String pathStr = "";
		for(String str : path)
			pathStr += str + "->";
		return pathStr.substring(0,pathStr.length()-2);
	}
}