package com.kent.springdatajpademo.repository;

import com.kent.springdatajpademo.entity.Course;
import com.kent.springdatajpademo.entity.Guardian;
import com.kent.springdatajpademo.entity.Student;
import com.kent.springdatajpademo.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
@Slf4j
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {

        List<Course> courses = courseRepository.findAll();
        log.info("courses size {}", courses.size());
        log.info("courses {}", courses);
    }

    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Max")
                .lastName("Well")
                .build();

        Course course = Course.builder()
                .courseTitle("React")
                .credit(3)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }



    @Test
    public void saveCourseWithTeacherTwo() {

        Teacher teacher = Teacher.builder()
                .firstName("Joe")
                .lastName("Dye")
                .build();


        Course course = Course.builder()
                .courseTitle("Redux")
                .credit(3)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findALlPagination() {

        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        int totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        log.info("totalElements = {}", totalElements);
        log.info("totalPages = {}", totalPages);

        log.info("courses size = {}", courses.size());
        log.info("courses = {}", courses);

    }

    @Test
    public void findAllPaginationWithSorting() {

        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("courseTitle"));

        Pageable sortByCreditDescending = PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("courseTitle").descending()
                .and(Sort.by("credit").descending()));

        List<Course> sortByTitleResults = courseRepository.findAll(sortByTitle).getContent();
        log.info("sortByTitleResults = {}", sortByTitleResults);

        List<Course> sortByCreditDescendingResults = courseRepository.findAll(sortByCreditDescending).getContent();
        log.info("sortByCreditDescendingResults = {}", sortByCreditDescendingResults);

        List<Course> sortByTitleAndCreditDescResults = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        log.info("sortByTitleAndCreditDescResults = {}", sortByTitleAndCreditDescResults);
    }

    @Test
    public void findByTitleContainingTest() {

        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findByCourseTitleContaining("D", firstPageTenRecords).getContent();
        log.info("courses = {}", courses);

    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Guardian guardian = Guardian.builder()
                .name("Mary")
                .email("mary@gmail.com")
                .mobile("09123345345")
                .build();


        Course course = Course.builder()
                .courseTitle("Bash Scripting")
                .credit(4)
                .students(List.of(Student.builder()
                        .firstName("Jane")
                        .lastName("doe")
                        .emailId("jane.doe@outlook.com")
                        .guardian(guardian)
                        .build()))
                .teacher(Teacher.builder().firstName("prasad").lastName("prahappy").build())
                .build();

        courseRepository.save(course);
    }
}