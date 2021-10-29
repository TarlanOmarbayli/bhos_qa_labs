package com.project.springproj3;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServ {

    @Test
    public void testInsertion() throws ExecutionException, InterruptedException {
        Course new_course = new Course("DBMS", "Mahammad Sharifli", 6);
        CourseServ.addCourse(new_course);
        Course course_get = CourseServ.readCourse("DBMS");

        assertEquals(new_course.name, course_get.name);
        assertEquals(new_course.tutor, course_get.tutor);
        assertEquals(new_course.credits, course_get.credits);

    }

}
