package ru.rg.groupqueuesserver.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rg.groupqueuesserver.subjects.SubjectEntity;
import ru.rg.groupqueuesserver.subjects.SubjectRepo;
import ru.rg.groupqueuesserver.subjects.dto.SubjectStudent;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;

    public List<StudentEntity> findAll() {
        return studentRepo.findAll();
    }
    public StudentEntity findById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }
    public StudentEntity save(StudentEntity student) {
        StudentEntity resStudent = studentRepo.save(student);
        List<SubjectEntity> subjects = subjectRepo.findAll();

        for (SubjectEntity subject : subjects) {
            subject.getStudents().add(
                    SubjectStudent.builder()
                            .studentId(resStudent.getId())
                            .status("WAITING")
                            .build()
            );

            subjectRepo.save(subject);
        }

        return resStudent;
    }
    public StudentEntity change(Long id, StudentEntity student) {
        if (studentRepo.existsById(id)) {
            student.setId(id);
            return studentRepo.save(student);
        };

        return null;
    }
    public void deleteById(Long id) {
        StudentEntity dbStudent = this.findById(id);
        if (dbStudent != null) {
            studentRepo.deleteById(id);
        }
    }
}
