import java.util.*;
import java.io.*;

public class Paths{
	static Map<String,List<String>> routes = new Hashtable<String,List<String>>();
	static{
		List<String> bangalore = new ArrayList<String>();
		bangalore.add("singapore");

		List<String> singapore = new ArrayList<String>();
		singapore.add("seoul");
		singapore.add("bangalore");
		singapore.add("dubai");

		List<String> seoul = new ArrayList<String>();
		seoul.add("beijing");
		seoul.add("singapore");

		List<String> beijing = new ArrayList<String>();
		beijing.add("tokyo");
		beijing.add("seoul");

		List<String> tokyo = new ArrayList<String>();
		tokyo.add("beijing");

		List<String> dubai = new ArrayList<String>();
		tokyo.add("singapore");		

		routes.put("bangalore",bangalore);
		routes.put("singapore",singapore);
		routes.put("seoul",seoul);
		routes.put("beijing",beijing);
		routes.put("tokyo",tokyo);
		routes.put("dubai",dubai);
	}

	public static boolean isCityPresent(String city){
		Set<String> keyset = routes.keySet();
		if(keyset.contains(city)) return true;
		else{
			for (String src: keyset) {
				List<String> destinations = routes.get(src);
				if(destinations.contains(city))
					return true;	
			}
		}
		return false;
	}

	public static boolean isDirectFlight(String src, String dest){
		List<String> destination = routes.get(src);
		if(destination != null)
			return destination.contains(dest);
		return false;
	}

	public static void main(String[] args) {
		String src = args[0].toLowerCase();
		String dest = args[1].toLowerCase();
		if(!isCityPresent(src)){
			System.out.println("No city named "+ src +" in database");
			return;
		}
		if(!isCityPresent(dest)){
			System.out.println("No city named "+ dest +" in database");
			return;
		}
		FindPath path = new FindPath();
		path.pathFinder(src,dest);
		System.out.println(path.pathToString(path.flightPath));
	}
}

class MyFileReader{
	Map<String,List<String>> fileRoutes = new Hashtable<String,List<String>>();
	public Map<String,List<String>> readFile(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null){
				createRoutes(line);
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("No database named "+fileName+" found.");
		}
		return this.fileRoutes;
	}

	public void createRoutes(String line){
		line = line.replaceAll("\\s+","");
		String cities[] = line.split(",");
		List<String> allDestinations = fileRoutes.get(cities[0]);
		if(allDestinations == null){
			allDestinations = new ArrayList<String>();
			for (int i=1; i<cities.length; i++)
				allDestinations.add(cities[i]);
			fileRoutes.put(cities[0],allDestinations);
		}
		else{
			for (int i=1; i<cities.length; i++){
				if(!allDestinations.contains(cities[i]))
					allDestinations.add(cities[i]);
			}
		}
	}
}