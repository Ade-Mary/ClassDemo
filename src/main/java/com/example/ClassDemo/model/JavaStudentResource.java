package com.example.ClassDemo.model;

import org.springframework.hateoas.RepresentationModel;

public class JavaStudentResource extends RepresentationModel<JavaStudentResource> {
    private JavaStudent javaStudent;


    public void setJavaStudent(JavaStudent javaStudent) {
        this.javaStudent = javaStudent;
    }
}
