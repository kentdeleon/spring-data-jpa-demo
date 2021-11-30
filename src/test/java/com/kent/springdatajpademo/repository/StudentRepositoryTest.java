package com.kent.springdatajpademo.repository;

import com.kent.springdatajpademo.entity.Guardian;
import com.kent.springdatajpademo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void testSaveStudent() {

        Student student = Student.builder()
                .firstName("Kent")
                .lastName("De Leon")
                .emailId("kent.deleon@gmail.com")
//                .guardianName("Albert")
//                .guardianEmail("albert@gmail.com")
//                .guardianMobile("09123345345")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void testSaveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("John")
                .email("john@gmail.com")
                .mobile("09123345345")
                .build();

        Student student = Student.builder()
                .firstName("Dave")
                .lastName("Doe")
                .emailId("dave.doe@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {

        List<Student> students = studentRepository.findAll();

        log.info("Students = {}", students);
    }

    @Test
    public void printStudentByFirstName() {

        List<Student> students = studentRepository.findByFirstName("Dave");
        log.info("Students = {}", students);

    }

    @Test
    public void printStudentByFirstNameContaining() {

        List<Student> students = studentRepository.findByFirstNameContaining("e");
        log.info("Students size = {}", students.size());
        log.info("Students = {}", students);

    }

    @Test
    public void printStudentByLastNameNotNull() {

        List<Student> students = studentRepository.findByLastNameNotNull();
        log.info("Students size = {}", students.size());
        log.info("Students = {}", students);

    }

    @Test
    public void printStudentByGuardianName() {

        List<Student> students = studentRepository.findByGuardianName("Albert");
        log.info("Students size = {}", students.size());
        log.info("Students = {}", students);

    }

    @Test
    public void printGetStudentByEmailAddress() {

        Student student = studentRepository.getStudentByEmailAddress("kent.deleon@gmail.com");
        log.info("Student = {}", student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress() {
       String firstName = studentRepository.getStudentFirstNameByEmailAddress("kent.deleon@gmail.com");
       log.info("firstName = {}", firstName);
    }

    @Test
    public void getStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("kent.deleon@gmail.com");
        log.info("Student = {}", student);
    }

    @Test
    public void getStudentByEmailAddressNativeNameParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNameParam("dave.doe@gmail.com");
        log.info("Student = {}", student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        int louis = studentRepository.updateStudentNameByEmailId("Louis", "test.deleon@gmail.com");
        log.info("louis = {}", louis);

    }
}