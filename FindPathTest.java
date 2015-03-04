import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FindPathTest {
	@Test
    public void test_isDirectFlight_should_return_false_if_src_is_london_and_dest_is_singapore(){
        FindPath path = new FindPath();
        List<String> flightPath;
        flightPath = path.pathFinder("bangalore","singapore");
        assertEquals("bangalore",flightPath.get(0));
        assertEquals("singapore",flightPath.get(1));
    }
}