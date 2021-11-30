package com.kent.springdatajpademo.repository;

import com.kent.springdatajpademo.entity.Course;
import com.kent.springdatajpademo.entity.CourseMaterial;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial(){

        Course course = Course.builder()
                .courseTitle("DSA")
                .credit(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        CourseMaterial record = courseMaterialRepository.save(courseMaterial);

        log.info("record {}", record);
    }

    @Test
    public void printAllCourseMaterials() {

        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        log.info("courseMaterials size {}", courseMaterials.size());
        log.info("courseMaterials {}", courseMaterials);
    }
}
