import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PathsTest {
    @Test
    public void test_isCityPresent_should_return_true_if_bangalore_present_in_map(){
        Paths path = new Paths();
        assertTrue(path.isCityPresent("bangalore"));
    }

    @Test
    public void test_isCityPresent_should_return_true_if_singapore_present_in_map(){
        Paths path = new Paths();
        assertTrue(path.isCityPresent("singapore"));
    }

    @Test
    public void test_isCityPresent_should_return_false_if_london_do_not_present_in_map(){
        Paths path = new Paths();
        assertFalse(path.isCityPresent("london"));
    }

    @Test
    public void test_isCityPresent_should_return_false_if_pune_do_not_present_in_map(){
        Paths path = new Paths();
        assertFalse(path.isCityPresent("pune"));
    }
}