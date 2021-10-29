package com.project.springproj3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CourseController {
    @Autowired
    CourseController courseController;

    @GetMapping("/course/{name}")
    public Course getCourse(@PathVariable String name) throws ExecutionException, InterruptedException {
        return CourseServ.readCourse(name);
    }
    @PostMapping("/course")
    public String postCourse(@RequestBody Course course) throws ExecutionException, InterruptedException {
        return CourseServ.addCourse(course);
    }



}
