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
        Queue<String> cityAndCountry = path.addCountryToCity(path.flightPath);
        assertTrue(path.flightPath.size() == 4);
        assertEquals("singapore[singapore]",cityAndCountry.poll());
        assertEquals("tokyo[japan]",cityAndCountry.poll());
        assertEquals("seoul[south korea]",cityAndCountry.poll());
        assertEquals("beijing[china]",cityAndCountry.poll());
    }

    @Test
    public void test_getPath_should_return_path_from_bangalore_to_dubai_with_countries(){
        FindPath path = new FindPath();
        assertTrue(path.pathFinder("bangalore","dubai"));
        path.readCountryFromFile("cities.txt");
        String expectedPath = path.pathToString(path.addCountryToCity(path.flightPath));
        assertEquals("bangalore[india]->singapore[singapore]->dubai[uae]",expectedPath);
    }

    @Test
    public void test_allPathFinder_should_return_2_possible_paths_seoul_to_singapore(){
        FindPath path = new FindPath();
        List<Queue<String>> allPaths = path.allPathFinder("seoul","singapore");
        Queue<String> path1 = allPaths.get(0);
        Queue<String> path2 = allPaths.get(1);
        assertEquals(allPaths.size(),2);
        assertEquals(path1.poll(),"seoul");
        assertEquals(path1.poll(),"beijing");
        assertEquals(path1.poll(),"tokyo");
        assertEquals(path1.poll(),"singapore");
        assertEquals(path2.poll(),"seoul");
        assertEquals(path2.poll(),"singapore");
    }

    @Test
    public void test_allPathFinder_should_return_2_possible_paths_singapore_to_tokyo(){
        FindPath path = new FindPath();
        List<Queue<String>> allPaths = path.allPathFinder("tokyo","singapore");
        Queue<String> path1 = allPaths.get(0);
        Queue<String> path2 = allPaths.get(1);
        assertEquals(allPaths.size(),2);
        assertEquals(path1.poll(),"tokyo");
        assertEquals(path1.poll(),"beijing");
        assertEquals(path1.poll(),"seoul");
        assertEquals(path1.poll(),"singapore");
        assertEquals(path2.poll(),"tokyo");
        assertEquals(path2.poll(),"singapore");
    }

    @Test
    public void test_allPathFinder_should_return_1_possible_paths_singapore_to_tokyo(){
        FindPath path = new FindPath();
        List<Queue<String>> allPaths = path.allPathFinder("bangalore","beijing");
        Queue<String> maidenPath = allPaths.get(0);
        assertEquals(allPaths.size(),1);
        assertEquals(maidenPath.poll(),"bangalore");
        assertEquals(maidenPath.poll(),"singapore");
        assertEquals(maidenPath.poll(),"seoul");
        assertEquals(maidenPath.poll(),"beijing");
    }

    @Test
    public void test_allPathToString_should_return_2_possible_paths_singapore_to_tokyo_as_string(){
        FindPath path = new FindPath();
        List<Queue<String>> allPaths = path.allPathFinder("tokyo","singapore");
        String expectedPath = "tokyo->beijing->seoul->singapore\ntokyo->singapore\n";
        assertEquals(path.allPathToString(allPaths),expectedPath);
    }
}