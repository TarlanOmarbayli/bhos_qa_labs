import my_classroom.Classroom;
import java.util.*;
import java.lang.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


 class ClassroomTest {
    Classroom infosec = new Classroom(2018,"Infosec",25,24,240,"Khayyam Masiyev");

    @Test
    void test1(){
        assertEquals(2018, infosec.getEntryYear());
    }
    @Test
    void test2(){
        assertEquals(1920, infosec.getMinimumTotalCredits());
    }
    @Test
    void test3(){
        assertEquals(25, infosec.getTotalNumberOfStudents());
    }
    @Test
    void test4(){
        assertEquals(2023, infosec.getGraduationYear());
    }
    @Test
    void test5(){
        assertEquals("Khayyam Masiyev", infosec.getNameOfCurator());
    }
}