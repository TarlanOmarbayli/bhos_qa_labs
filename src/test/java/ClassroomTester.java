import my_classroom.Classroom;
import java.util.*;
import java.lang.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ClassroomTester {
    Classroom infosec = new Classroom(2018,"Infosec",25,24,240,"Khayyam Masiyev");

    @Test
    public void test1(){
        assertEquals(2018, infosec.getEntry_year());
    }
    @Test
    public void test2(){
        assertEquals(1920, infosec.getMinimumTotalCredits());
    }
    @Test
    public void test3(){
        assertEquals("Infosec 2018", infosec.getName_of_classroom());
    }
    @Test
    public void test4(){
        assertEquals(2023, infosec.getGraduationYear());
    }
    @Test
    public void test5(){
        assertEquals("Khayyam Masiyev", infosec.getName_of_curator());
    }


}