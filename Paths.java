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

	public List<String> fileSeperator(String[] args){
		List<String> fileList = new ArrayList<String>();
		for (String file: args){
			if((file.length()) > 0 && (new File(file).exists()))
				fileList.add(file);
		}
		return fileList;
	}

	public static void main(String[] args) {
		String src = args[0].toLowerCase();
		String dest = args[1].toLowerCase();
		FindPath fpath = new FindPath();
		boolean isCountryRead = false;
		if(args[0].equals("-f")){
			routes.clear();
			MyFileReader reader = new MyFileReader();
			routes = reader.readFile(args[1]);
			if(routes.isEmpty()) return;
			if(args[2].equals("-c")){
				isCountryRead = fpath.readCountryFromFile(args[3]);
				if(isCountryRead == false) return;
				src = args[4].toLowerCase();
				dest = args[5].toLowerCase();
			}
			else{
				src = args[2].toLowerCase();
				dest = args[3].toLowerCase();
			}
		}
		if(!isCityPresent(src)){
			System.out.println("No city named "+ src +" in database");
			return;
		}
		if(!isCityPresent(dest)){
			System.out.println("No city named "+ dest +" in database");
			return;
		}
		fpath.pathFinder(src,dest);
		if(isCountryRead == true){ fpath.addCountryToCity();}
		System.out.println(fpath.pathToString(fpath.flightPath));
	}
}

class MyFileReader{
	Map<String,List<String>> fileRoutes = new Hashtable<String,List<String>>();

	public Map<String,List<String>> readFile(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
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
		String cities[] = lineConvertToArray(line);
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

	public String[] lineConvertToArray(String line){
		String cities[] = line.split(",");
		for (int i=0; i<cities.length; i++)
			cities[i] = cities[i].trim().toLowerCase();
		return cities;
	}
}