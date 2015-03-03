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
		List<String> source = routes.get(src);
		if(source != null)
			return source.contains(dest);
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
		System.out.println(isDirectFlight(src,dest));
	}
}