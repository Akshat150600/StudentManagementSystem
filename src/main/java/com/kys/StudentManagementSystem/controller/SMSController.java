package com.kys.StudentManagementSystem.controller;

import com.kys.StudentManagementSystem.model.Student;
import com.kys.StudentManagementSystem.repository.StudentRepository;
import com.kys.StudentManagementSystem.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/com.sms")
public class SMSController {

    @Autowired
    private StudentRepository repository;

    @PostMapping("/add-student")
    public String RegisterStudent(@RequestBody Student student){
            if(repository.existsById(student.getStudentId())){
                return "Student Already Exist";
            }
            else{
                repository.save(student);
            }
        return "Student Added Successfully";
    }

    @GetMapping("/view-students")
    public List<Student> FetchStudents(){
        return repository.findAll();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @GetMapping("/find-student/{id}")
    public Student FindStudentByID(@PathVariable Integer id){
        if(repository.findById(id)==null){
            throw new StudentNotFoundException("Student Doesn't Exist");
        }else {
            return repository.findById(id).orElse(null);
        }
    }
}
