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
    public void test_getPath_should_return_path_from_bangalore_to_dubai(){
        FindPath path = new FindPath();
        assertTrue(path.pathFinder("bangalore","dubai"));
        assertEquals("bangalore->singapore->dubai",path.pathToString(path.flightPath));
    }
}