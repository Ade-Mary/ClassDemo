package com.example.ClassDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "java_student")
public class JavaStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "First name can not be null")
    @NotNull(message = "First name can not be blank")//"a"
    @Length(min = 3, message = "Name can not be less than 3 characters")
    @Column(name =  "first_name")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @NotNull(message = "Last name can not be blank")//"a"
    @Length(min = 3, message = "Last Name can not be less than 3 characters")
    @Column(name =  "last_name")
    private String lastName;


    @NotNull(message = "sex can not be null")
    @NotNull(message = "sex can not be blank")//"a"
    @Length(min = 3, message = "sex can not be less than 3 characters")
    @Length(min = 4, message = "Sex can not be less than 4 characters")
    private String sex;


    @NotNull
    @Min(value = 18, message = "You must be 18 years and above")
    @Max(value = 70, message = "You are too old")
    private int age;


    @Email(message = "Please enter a valid email address")
    private String email;


    @Pattern(regexp = "Java[0-9]{4}", message = "Please follow naming convention 'Java###'")
    private String course;



    public JavaStudent(String firstName, String lastName, String sex, int age, String email, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age=age;
        this.id=id;
        this.email = email;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "JavaStudent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", course='" + course + '\'' +
                '}';


    }
}


