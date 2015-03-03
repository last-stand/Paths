import java.util.*;

public class Paths{
	static Map<String,List<String>> routes = new Hashtable<String,List<String>>();
	static{
		List<String> bangalore = new ArrayList<String>();
		bangalore.add("singapore");

		routes.put("bangalore",bangalore);
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
		if(isCityPresent(src)){
			List<String> source = routes.get(src);
			return source.contains(dest);
		}
		return false;
	}
}