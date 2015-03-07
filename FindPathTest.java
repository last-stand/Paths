import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FindPathTest {
	@Test
    public void test_isDirectFlight_should_return_false_if_src_is_london_and_dest_is_singapore(){
        FindPath path = new FindPath("bangalore","singapore");
        List<String> flightPath;
        assertSame(1,path.pathFinder());
        assertEquals("bangalore",path.flightPath.poll());
        assertEquals("singapore",path.flightPath.poll());
    }

    @Test
    public void test_getPath_should_return_path_from_bangalore_to_singapre(){
        FindPath path = new FindPath("bangalore","singapore");
        assertSame(1,path.pathFinder());
        assertEquals("bangalore->singapore",path.getPath(path.flightPath));
    }

    @Test
    public void test_getPath_should_return_path_from_singapre_to_tokyo(){
        FindPath path = new FindPath("singapore","tokyo");
        Queue<String> flightPath = new LinkedList<String>();
        flightPath.add("singapore");
        flightPath.add("seoul");
        flightPath.add("beijing");
        flightPath.add("tokyo");
        assertEquals("singapore->seoul->beijing->tokyo",path.getPath(flightPath));
    }
}