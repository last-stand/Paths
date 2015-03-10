import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FindPathTest {
	@Test
    public void test_isDirectFlight_should_return_false_if_src_is_london_and_dest_is_singapore(){
        FindPath path = new FindPath();
        List<String> flightPath;
        assertTrue(path.pathFinder("bangalore","singapore"));
        assertEquals("bangalore",path.flightPath.poll());
        assertEquals("singapore",path.flightPath.poll());
    }

    @Test
    public void test_getPath_should_return_path_from_bangalore_to_singapre(){
        FindPath path = new FindPath();
        assertTrue(path.pathFinder("bangalore","singapore"));
        assertEquals("bangalore->singapore",path.pathToString(path.flightPath));
    }

    @Test
    public void test_getPath_should_return_path_from_singapre_to_tokyo(){
        FindPath path = new FindPath();
        Queue<String> flightPath = new LinkedList<String>();
        flightPath.add("singapore");
        flightPath.add("seoul");
        flightPath.add("beijing");
        flightPath.add("tokyo");
        assertEquals("singapore->seoul->beijing->tokyo",path.pathToString(flightPath));
    }

    @Test
    public void test_getPath_should_return_path_from_bangalore_to_tokyo(){
        FindPath path = new FindPath();
        assertTrue(path.pathFinder("bangalore","tokyo"));
        assertEquals("bangalore->singapore->seoul->beijing->tokyo",path.pathToString(path.flightPath));
    }

    @Test
    public void test_getPath_should_return_path_from_tokyo_to_bangalore(){
        FindPath path = new FindPath();
        assertTrue(path.pathFinder("tokyo","bangalore"));
        assertEquals("tokyo->beijing->seoul->singapore->bangalore",path.pathToString(path.flightPath));
    }

    @Test
    public void test_readCountryFromFile_should_put_city_and_country_in_hash_table(){
        FindPath path = new FindPath();
        path.readCountryFromFile("cities.txt");
        assertTrue(path.cityWithCountry.size() == 6);
        assertEquals(path.cityWithCountry.get("bangalore"),"india");
        assertEquals(path.cityWithCountry.get("tokyo"),"japan");
        assertEquals(path.cityWithCountry.get("singapore"),"singapore");
        assertEquals(path.cityWithCountry.get("seoul"),"south korea");
        assertEquals(path.cityWithCountry.get("dubai"),"uae");
        assertEquals(path.cityWithCountry.get("beijing"),"china");
    }

    @Test
    public void test_addCountryToCity_should_add_country_after_city_name(){
        FindPath path = new FindPath();
        path.flightPath.add("singapore");
        path.flightPath.add("tokyo");
        path.flightPath.add("seoul");
        path.flightPath.add("beijing");
        path.readCountryFromFile("cities.txt");
        path.addCountryToCity();
        assertTrue(path.flightPath.size() == 4);
        assertEquals("singapore[singapore]",path.flightPath.poll());
        assertEquals("tokyo[japan]",path.flightPath.poll());
        assertEquals("seoul[south korea]",path.flightPath.poll());
        assertEquals("beijing[china]",path.flightPath.poll());
    }

    @Test
    public void test_getPath_should_return_path_from_bangalore_to_dubai_with_countries(){
        FindPath path = new FindPath();
        assertTrue(path.pathFinder("bangalore","dubai"));
        path.readCountryFromFile("cities.txt");
        path.addCountryToCity();
        assertEquals("bangalore[india]->singapore[singapore]->dubai[uae]",path.pathToString(path.flightPath));
    }
}