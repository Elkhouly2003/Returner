package com.example.Returner.services;

import com.example.Returner.dtos.CourseResponse;
import com.example.Returner.dtos.StudentRequest;
import com.example.Returner.dtos.StudentResponse;
import com.example.Returner.execptions.ResourceNotFoundException;
import com.example.Returner.models.Course;
import com.example.Returner.models.Student;
import com.example.Returner.repositories.CourseRepository;
import com.example.Returner.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentResponse> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();

        List<StudentResponse>students= new ArrayList<>();
        for (Student student : allStudents) {
            StudentResponse studentResponse = getStudentById(student.getId());
            students.add(studentResponse);
        }


        return students;
    }

    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student with id " + studentId + " not found")
        );
        List<CourseResponse> courseResponses =
                student.getCourses()
                        .stream()
                        .map(course -> {
                            CourseResponse response = new CourseResponse();
                            response.setId(course.getId());
                            response.setName(course.getName());
                            response.setCode(course.getCode());
                            response.setNHours(course.getNHours());
                            return response;
                        })
                        .toList();
        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setId(student.getId());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setCourses(courseResponses);
        studentResponse.setGender(student.getGender());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhone(student.getPhone());

        return studentResponse;

    }

    public void addStudent(StudentRequest studentRequest) {
        Student student = new Student();

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setGender(studentRequest.getGender());
        student.setAddress(studentRequest.getAddress());
        student.setPhone(studentRequest.getPhone());
        student.setAge(studentRequest.getAge());

        studentRepository.save(student);
    }
    public void updateStudent(Long studentId,StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResolutionException("Student not found")
        );
        if(studentRequest.getFirstName() != null) {
            student.setFirstName(studentRequest.getFirstName());
        }
        if(studentRequest.getLastName() != null) {
            student.setLastName(studentRequest.getLastName());
        }

        if(studentRequest.getGender() != null) {
            student.setGender(studentRequest.getGender());
        }
        if(studentRequest.getAddress() != null) {
            student.setAddress(studentRequest.getAddress());
        }
        if(studentRequest.getPhone() != null) {
            student.setPhone(studentRequest.getPhone());
        }
        if (studentRequest.getAge() != null){
            student.setAge(studentRequest.getAge());
        }

        studentRepository.save(student);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void enrolInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not found")
        );
        Course course =courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course not found")
        );
        student.getCourses().add(course);
        studentRepository.save(student);

    }
}
