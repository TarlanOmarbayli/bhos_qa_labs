package com.project.springproj3;

import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.ExecutionException;

public class TestServ {

    @Test
    public void testInsertion() throws ExecutionException, InterruptedException {
        Course new_course = new Course("DBMS", "Mahammad Sharifli", 6);
        CourseServ.addCourse(new_course);
        Course course_get = CourseServ.readCourse("DBMS");

        assert course_get != null;
        Assert.assertTrue(new_course.name==course_get.name
                && new_course.tutor==course_get.tutor
                && new_course.credits==course_get.credits);
    }

}
