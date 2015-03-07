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
    public void test_isCityPresent_should_return_true_if_tokyo_present_in_map(){
        Paths path = new Paths();
        assertTrue(path.isCityPresent("tokyo"));
    }

    @Test
    public void test_isCityPresent_should_return_false_if_london_do_not_present_in_map(){
        Paths path = new Paths();
        assertFalse(path.isCityPresent("london"));
    }

    @Test
    public void test_isCityPresent_should_return_false_if_stockholm_do_not_present_in_map(){
        Paths path = new Paths();
        assertFalse(path.isCityPresent("stockholm"));
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
    public void test_isDirectFlight_should_return_true_if_src_is_tokyo_and_dest_is_beijing(){
        Paths path = new Paths();
        assertTrue(path.isDirectFlight("tokyo","beijing"));
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

    @Test
    public void test_createRoutes_should_put_list_string_in_map_if_not_exist(){
        MyFileReader reader = new MyFileReader();
        reader.createRoutes("lucknow,delhi,chennai");
        Map<String,List<String>> fileRoutes = reader.fileRoutes;
        assertTrue(fileRoutes.containsKey("lucknow"));
        assertEquals("delhi",fileRoutes.get("lucknow").get(0));
        assertEquals("chennai",fileRoutes.get("lucknow").get(1));
    }

    @Test
    public void test_createRoutes_should_put_multiple_list_string_in_map_if_not_exist(){
        MyFileReader reader = new MyFileReader();
        reader.createRoutes("lucknow,delhi,chennai");
        reader.createRoutes("bangalore,kolkata,jaipur");
        Map<String,List<String>> fileRoutes = reader.fileRoutes;
        assertTrue(fileRoutes.containsKey("lucknow"));
        assertTrue(fileRoutes.containsKey("bangalore"));
        assertEquals("delhi",fileRoutes.get("lucknow").get(0));
        assertEquals("chennai",fileRoutes.get("lucknow").get(1));
        assertEquals("kolkata",fileRoutes.get("bangalore").get(0));
        assertEquals("jaipur",fileRoutes.get("bangalore").get(1));
    }

    @Test
    public void test_createRoutes_should_create_single_map_with_lucknow_as_key_which_contains_all_cities(){
        MyFileReader reader = new MyFileReader();
        reader.createRoutes("lucknow,delhi,chennai,allahabad");
        reader.createRoutes("lucknow,kanpur,jammu");
        Map<String,List<String>> fileRoutes = reader.fileRoutes;
        assertTrue(fileRoutes.containsKey("lucknow"));
        assertEquals("delhi",fileRoutes.get("lucknow").get(0));
        assertEquals("chennai",fileRoutes.get("lucknow").get(1));
        assertEquals("allahabad",fileRoutes.get("lucknow").get(2));
        assertEquals("kanpur",fileRoutes.get("lucknow").get(3));
        assertEquals("jammu",fileRoutes.get("lucknow").get(4));
    }

    @Test
    public void test_readFile_should_read_text_file_and_return_map_with_first_word_as_key(){
        MyFileReader reader = new MyFileReader();
        Map<String,List<String>> mapPath = reader.readFile("paths.txt");
        Set<String> cities = mapPath.keySet();
        assertTrue(cities.contains("bangalore"));
        assertTrue(cities.contains("singapore"));
        assertTrue(cities.contains("seoul"));
        assertTrue(cities.contains("beijing"));
        assertSame(4,cities.size());
    }

    @Test
    public void test_readFile_should_return_map_with_unique_cities_but_all_related_destinations(){
        MyFileReader reader = new MyFileReader();
        Map<String,List<String>> mapPath = reader.readFile("paths.txt");
        List<String> bangalore = mapPath.get("bangalore");
        List<String> singapore = mapPath.get("singapore");
        List<String> seoul = mapPath.get("seoul");
        List<String> beijing = mapPath.get("beijing");
        assertSame(1,bangalore.size());
        assertSame(2,singapore.size());
        assertSame(1,seoul.size());
        assertSame(1,beijing.size());
    }

    @Test
    public void test_readFile_should_return_map_with_cities_and_theirs_all_destinations(){
        MyFileReader reader = new MyFileReader();
        Map<String,List<String>> mapPath = reader.readFile("paths.txt");
        List<String> bangalore = mapPath.get("bangalore");
        List<String> singapore = mapPath.get("singapore");
        List<String> seoul = mapPath.get("seoul");
        List<String> beijing = mapPath.get("beijing");
        assertEquals("singapore",bangalore.get(0));
        assertEquals("seoul",singapore.get(0));
        assertEquals("dubai",singapore.get(1));
        assertEquals("beijing",seoul.get(0));
        assertEquals("tokyo",beijing.get(0));
    }
}