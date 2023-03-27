package ru.rg.groupqueuesserver.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, Long> {
    @Query("select sub, stu from SubjectEntity sub " +
            "join fetch sub.students ss " +
            "join fetch StudentEntity stu on ss.studentId = stu.id " +
            "where sub.id = :id")
    List<Object[]> findByIdStudents(@Param("id") Long id);
}
