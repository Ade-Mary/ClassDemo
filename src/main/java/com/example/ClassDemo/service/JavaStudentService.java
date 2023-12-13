package com.example.ClassDemo.service;


import com.example.ClassDemo.Repository.JavaStudentRepository;
import com.example.ClassDemo.exception.StudentNotFoundException;

import com.example.ClassDemo.model.JavaStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class JavaStudentService {

    @Autowired
    private JavaStudentRepository javaStudentRepository;

    public ResponseEntity<JavaStudent> saveStudent(JavaStudent javaStudent){
//        javaStudentRepository.save(javaStudent);
        return ResponseEntity.ok(javaStudentRepository.save(javaStudent));
    }

    public ResponseEntity<String> saveMultipleStudents(List<JavaStudent> javaStudentList){
//        for( JavaStudent st: javaStudentList ){
//            javaStudentRepository.save(st);
//        }
        javaStudentRepository.saveAll(javaStudentList);
        return ResponseEntity.ok("All students successfully registered");
    }

    public ResponseEntity<List<JavaStudent>> getAllStudents(){
        return ResponseEntity.ok(javaStudentRepository.findAll());
    }

    public JavaStudent getOne(int id){
        return getStudentById(id).getBody();
    }

    public ResponseEntity<JavaStudent> getStudentById(Integer id){
//        JavaStudent student = javaStudentRepository.findById(id).orElse(null);
        return ResponseEntity.ok(javaStudentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Java Student Not found")));
    }

    public ResponseEntity<JavaStudent> getStudentByEmail(String email){
        JavaStudent student = javaStudentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException(String.format("Java Student with %s email was not found", email)));
        return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
    }

    public List<JavaStudent> getStudentsByFirstName(String firstName){
        return javaStudentRepository.findByFirstName(firstName);
    }

    public JavaStudent updateStudentsInfo(int id, JavaStudent javaStudent){
        JavaStudent toBeUpdated = javaStudentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student not found"));
        //Update variables
        assert toBeUpdated != null;
        toBeUpdated.setFirstName(javaStudent.getFirstName());
        toBeUpdated.setLastName(javaStudent.getLastName());
        toBeUpdated.setAge(javaStudent.getAge());
        toBeUpdated.setEmail(javaStudent.getEmail());
        toBeUpdated.setCourse(javaStudent.getCourse());
        toBeUpdated.setSex(javaStudent.getSex());

        return javaStudentRepository.save(toBeUpdated);
    }

    public ResponseEntity<String> deleteJavaStudent(int id){
        javaStudentRepository.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

}
