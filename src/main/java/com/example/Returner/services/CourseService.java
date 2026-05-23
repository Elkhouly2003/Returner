package com.example.Returner.services;

import com.example.Returner.dtos.CourseRequest;
import com.example.Returner.dtos.CourseResponse;
import com.example.Returner.execptions.ResourceNotFoundException;
import com.example.Returner.models.Course;
import com.example.Returner.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(CourseRequest courseRequest) {
        Course course = new Course();

        course.setName(courseRequest.getName());
        course.setCode(courseRequest.getCode());
        course.setNHours(courseRequest.getNHours());

        courseRepository.save(course) ;
    }

    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course with id " + courseId + " not found")
        );
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setCode(course.getCode());
        courseResponse.setNHours(course.getNHours());

        return courseResponse;
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
    public void updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        if(courseRequest.getName()!=null) {
            course.setName(courseRequest.getName());
        }
        if(courseRequest.getCode()!=null) {
            course.setCode(courseRequest.getCode());
        }
        if(courseRequest.getNHours()!=null){
            course.setNHours(courseRequest.getNHours());
        }
        courseRepository.save(course);
    }


}
