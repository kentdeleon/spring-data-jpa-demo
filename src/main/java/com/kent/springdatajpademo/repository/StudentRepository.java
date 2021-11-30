package com.kent.springdatajpademo.repository;

import com.kent.springdatajpademo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    //JPQL - attribute defined here are base on the class name and field names not by the table or column name
    @Query("SELECT s FROM Student s WHERE s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL - attribute defined here are base on the class name and field names not by the table or column name
    @Query("SELECT s.firstName FROM Student s WHERE s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //native query
    @Query(value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    //native query named param
    @Query(value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId", nativeQuery = true)
    Student getStudentByEmailAddressNativeNameParam(@Param("emailId") String emailId);


    @Modifying //annotate with this if we want to update existing record in table
    @Transactional //this is to let jpa know that this command will be update and commit something in the db
    //ideally this should be added in db service layer to cascade update for all tables related for this method call.
    @Query(value = "UPDATE tbl_student SET first_name = :firstName WHERE email_address = :emailId", nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);


}
