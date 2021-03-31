package com.example.controller;

import com.example.dto.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

    private List<Student> list= Arrays.asList(
            new Student(100,"AAA"),
            new Student(101,"BBB"),
            new Student(102,"CCC")
    );
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Integer id){

        return list.stream().filter(student->student.getStudentId().equals(id)).findFirst().orElseThrow(()->new IllegalStateException("No StudentID : " + id + " was found"));

    }
}
