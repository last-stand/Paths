import java.util.*;
import java.io.*;

public class FindPath extends Paths{
	Queue<String> flightPath = new LinkedList<String>();
	Map<String,String> cityWithCountry = new Hashtable<String,String>();

	public boolean pathFinder(String source, String destination){
		flightPath.add(source);
		return (getFlightPath(source,destination) == 1) ? true : false;
	}

	public int getFlightPath(String source, String destination){
		if(Paths.routes.get(source) == null) return 0;
		if(isDirectFlight(source,destination)){
		 	flightPath.add(destination);
		 	return 1;
		}
		List<String> allDestCities = new ArrayList<String>();
		allDestCities = Paths.routes.get(source);
		for (int i = 0; i < allDestCities.size(); i++) {
			if(!flightPath.contains(allDestCities.get(i))){
				flightPath.add(allDestCities.get(i));
				return getFlightPath(allDestCities.get(i),destination);
			}
		}
		return 0;
	}

	public List<Queue<String>> allPathFinder(String source, String destination){
		Queue<String> fullPath = new LinkedList<String>();
        List<Queue<String>> allPaths = new ArrayList<Queue<String>>();
        getAllFlightPath(fullPath, allPaths,source, destination);
        return allPaths;
	}

	public void getAllFlightPath(Queue<String> fullPath, List<Queue<String>> allPaths,String source, String destination){
		fullPath.add(source);
        if (source.equals(destination)) {
            allPaths.add(new LinkedList<String>(fullPath));
            fullPath.remove(source);
            return;
        }
        int size = routes.get(source).size();
        List<String> destinations = routes.get(source);
        for (int i = 0; i < destinations.size(); i++) {
            if (!fullPath.contains(destinations.get(i))) {
                getAllFlightPath(fullPath, allPaths, destinations.get(i), destination);
            }
        }
        fullPath.remove(source);
	}

	public boolean readCountryFromFile(String fileName){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			MyFileReader fr = new MyFileReader();
			String line = null;
			while ((line = br.readLine()) != null){
				String cities[] = fr.lineConvertToArray(line);
				if(!cityWithCountry.containsKey(cities[0]))
					cityWithCountry.put(cities[0],cities[1]);
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("No database named "+fileName+" found.");
			return false;
		}
		return true;
	}

	public Queue<String> addCountryToCity(Queue<String> allCities){
		Queue<String> cityAndCountry = new LinkedList<String>();
		for (String city: allCities) {
			 String country = cityWithCountry.get(city);
			 cityAndCountry.add(city+"["+country+"]");
		}
		return cityAndCountry;
	}

	public String allPathToString(List<Queue<String>> allFlightPaths){
		String allPaths = "";
		for (Queue<String> path : allFlightPaths)
			allPaths += pathToString(path) + "\n";
		return allPaths;
	}

	public static String pathToString(Queue<String> path){
		return String.join("->",path);
	}
}