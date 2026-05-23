package com.example.Returner.controllers;

import com.example.Returner.dtos.CourseRequest;
import com.example.Returner.models.Course;
import com.example.Returner.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
   public ResponseEntity<String> addCourse(@RequestBody CourseRequest courseRequest) {
        courseService.addCourse(courseRequest);
        return ResponseEntity.ok("new Course added successfully !");
   }

   @DeleteMapping("/{courseId}")
   public ResponseEntity<String>deleteCourse(@PathVariable Long courseId) {
         courseService.deleteCourse(courseId);
         return ResponseEntity.ok("Course deleted successfully !");
   }

   @PatchMapping("/{courseId}")
   public ResponseEntity<String> updateCourse(@PathVariable Long courseId,@RequestBody CourseRequest courseRequest) {
         courseService.updateCourse(courseId, courseRequest);
         return ResponseEntity.ok("Course updated successfully !");
   }


}
