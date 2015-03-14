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

	public boolean allPathFinder(String source, String destination){
		flightPath.add(source);
		return (getAllFlightPath(source,destination) == 1) ? true : false;
	}

	public int getAllFlightPath(String source, String destination){
		return 0;
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

	public void addCountryToCity(){
		Queue<String> cityAndCountry = new LinkedList<String>();
		for (String city: flightPath) {
			 String country = cityWithCountry.get(city);
			 cityAndCountry.add(city+"["+country+"]");
		}
		flightPath.clear();
		for (String city: cityAndCountry){
			flightPath.add(city);
		}
	}

	public static String pathToString(Queue<String> path){
		return String.join("->",path);
	}
}