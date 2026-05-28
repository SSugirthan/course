package com.example.course.Controller;

import com.example.course.Model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    List<Course> courses = new ArrayList<>();

    public CourseController() {

        courses.add(new Course("CS101", "Java", 4));
        courses.add(new Course("CS102", "Python", 3));
        courses.add(new Course("CS103", "DBMS", 4));
        courses.add(new Course("CS104", "Spring Boot", 5));
        courses.add(new Course("CS105", "AI", 3));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(courses);
    }
    @GetMapping("/{code}")
    public ResponseEntity<Course> getCourseByCode(
            @PathVariable String code) {

        for (Course c : courses) {

            if (c.getCourseCode().equalsIgnoreCase(code)) {

                return ResponseEntity.ok(c);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Course> searchCourse(
            @RequestParam String code) {

        for (Course c : courses) {

            if (c.getCourseCode().equalsIgnoreCase(code)) {

                return ResponseEntity.ok(c);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addCourse(
            @RequestBody Course course) {

        courses.add(course);

        return ResponseEntity.ok("Course Added");
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<String> updateCourse(@PathVariable
 String code, @RequestBody Course updatedCourse) {
        for (Course c : courses) {

            if (c.getCourseCode().equalsIgnoreCase(code)) {

                courses.remove(c);
                courses.add(updatedCourse);

                return ResponseEntity.ok("Course Updated");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCourse(@PathVariable String code){
        for(Course c: courses){

            if (c.getCourseCode().equalsIgnoreCase(code)) {

                courses.remove(c);

                return ResponseEntity.ok("Course Deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }
}

