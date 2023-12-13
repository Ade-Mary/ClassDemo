package com.example.ClassDemo.Controller;

//import com.example.ClassDemo.exception.studentNotFoundException;
import com.example.ClassDemo.model.JavaStudent;
import com.example.ClassDemo.model.JavaStudentResource;
import com.example.ClassDemo.service.JavaStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JavaStudentController { //Client -> Controller -> service -> repository -> database;


    @Autowired
    private JavaStudentService javaStudentService;

    @PostMapping("/students")
    public ResponseEntity<JavaStudent> saveStudent(@Valid @RequestBody JavaStudent javaStudent){
//        javaStudentRepository.save(javaStudent);
        return ResponseEntity.ok(javaStudentService.saveStudent(javaStudent).getBody());
    }

    @PostMapping("/allStudents")
    public ResponseEntity<String> saveMultipleStudents(@Valid @RequestBody List<JavaStudent> javaStudentList){
        return javaStudentService.saveMultipleStudents(javaStudentList);
    }

    @GetMapping(value = "/students/all")
    public ResponseEntity<List<JavaStudent>> getAllStudents(){
        return ResponseEntity.ok(javaStudentService.getAllStudents().getBody());
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<JavaStudent> getStudentById(@PathVariable Integer id){
//        JavaStudent student = javaStudentRepository.findById(id).orElse(null);
        return javaStudentService.getStudentById(id);
    }

    @GetMapping("/students/email/{email}")
    public ResponseEntity<JavaStudent> getStudentByEmail(@PathVariable String email){
        JavaStudent student = javaStudentService.getStudentByEmail(email).getBody();
        return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/test/{id}")
    public JavaStudent getStudentBasedOnId(@PathVariable int id){
        return javaStudentService.getOne(id);
    }


    @GetMapping("/students/names/{firstName}")
    public List<JavaStudent> getStudentsByFirstName(@PathVariable String firstName){
        return javaStudentService.getStudentsByFirstName(firstName);
    }

    @PutMapping("/student/{id}")
    public JavaStudent updateStudentsInfo(@PathVariable int id, @RequestBody @Valid JavaStudent javaStudent){
        return javaStudentService.updateStudentsInfo(id, javaStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteJavaStudent(@PathVariable int id){

        return javaStudentService.deleteJavaStudent(id);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<JavaStudentResource> getJavaStudentResource(@PathVariable int id){
        JavaStudent javaStudent = getStudentById(id).getBody();

        assert javaStudent != null;
        JavaStudentResource javaStudentResource = new JavaStudentResource();
        javaStudentResource.setJavaStudent(javaStudent);
        Link getById = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(JavaStudentController.class)
                .getStudentById(id)).withRel("getStudentById");
        Link deleteById = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(JavaStudentController.class)
                .deleteJavaStudent(id)).withRel("deleteById");
        Link allStudents = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(JavaStudentController.class).getAllStudents()).withRel("allStudents");
        Link getByEmail = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(JavaStudentController.class).getStudentByEmail(javaStudent.getEmail())).withRel("getByEmail");
        javaStudentResource.add(getById, deleteById, allStudents, getByEmail);
        return new ResponseEntity<>(javaStudentResource, HttpStatus.OK);
    }


}








