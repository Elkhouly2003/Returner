package com.example.Returner.controllers;

import com.example.Returner.dtos.StudentRequest;
import com.example.Returner.dtos.StudentResponse;
import com.example.Returner.models.Student;
import com.example.Returner.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest) {
        studentService.addStudent(studentRequest);
        return ResponseEntity.ok("Student added successfully");
    }
    @PatchMapping("/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable Long studentId, @RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(studentId, studentRequest);
        return ResponseEntity.ok("Student updated successfully");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }
    @PostMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<?>enrollStudentInCourse(@PathVariable Long studentId,@PathVariable Long courseId) {
         studentService.enrolInCourse(studentId, courseId);
         return ResponseEntity.ok("Student enrolled successfully in the course");
    }

}
