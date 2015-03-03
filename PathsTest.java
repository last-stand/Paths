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

    @Test
    public void test_isDirectFlight_should_return_true_if_src_is_bangalore_and_dest_is_singapore(){
        Paths path = new Paths();
        assertTrue(path.isDirectFlight("bangalore","singapore"));
    }

    @Test
    public void test_isDirectFlight_should_return_false_if_src_is_bangalore_and_dest_is_lahore(){
        Paths path = new Paths();
        assertFalse(path.isDirectFlight("bangalore","lahore"));
    }

    @Test
    public void test_isDirectFlight_should_return_false_if_src_is_london_and_dest_is_singapore(){
        Paths path = new Paths();
        assertFalse(path.isDirectFlight("london","lahore"));
    }
}