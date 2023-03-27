package ru.rg.groupqueuesserver.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rg.groupqueuesserver.students.StudentEntity;
import ru.rg.groupqueuesserver.students.StudentRepo;
import ru.rg.groupqueuesserver.subjects.dto.SubjectFullData;
import ru.rg.groupqueuesserver.subjects.dto.SubjectStudent;
import ru.rg.groupqueuesserver.subjects.dto.SubjectStudentFullData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepo subjectRepo;
    private final StudentRepo studentRepo;

    private SubjectFullData fullData(SubjectEntity subject) {
        SubjectFullData res = SubjectFullData.builder()
                .name(subject.getName())
                .id(subject.getId())
                .students(new ArrayList<>())
                .build();

        for (SubjectStudent subjectStudent : subject.getStudents()) {
            res.getStudents().add(
                    new SubjectStudentFullData(
                            studentRepo.findById(subjectStudent.getStudentId()).orElse(null),
                            subjectStudent.getStatus()
                    )
            );
        }

        return res;
    }

    public List<SubjectEntity> findAll() {
        return subjectRepo.findAll();
    }
    public SubjectFullData findById(Long id) {
        SubjectEntity subjectEntity = subjectRepo.findById(id).orElse(null);
        if (subjectEntity == null) {
            return null;
        }

        return this.fullData(subjectEntity);
    }
    public SubjectEntity save(SubjectEntity subject) {
        List<StudentEntity> students = studentRepo.findAll();

        subject.setStudents(students.stream()
                .map((student) -> {
                        return SubjectStudent.builder()
                                .status("WAITING")
                                .studentId(student.getId())
                                .build();
                }
        ).toList());

        return subjectRepo.save(subject);
    }
    public SubjectEntity change(Long id, SubjectEntity subject) {
        if (subjectRepo.existsById(id)) {
            subject.setId(id);
            return subjectRepo.save(subject);
        };

        return null;
    }
    public SubjectEntity changeStudent(Long subjectId, Long studentId, SubjectStudent student) {
        SubjectEntity subject = subjectRepo.findById(subjectId).orElse(null);
        if (subject == null) {
            return null;
        }

        for (SubjectStudent subjectStudent : subject.getStudents()) {
            if (Objects.equals(subjectStudent.getStudentId(), studentId)) {
                subjectStudent.setStatus(student.getStatus());
            }
        }

        return subjectRepo.save(subject);
    }
    public void deleteById(Long id) {
        subjectRepo.deleteById(id);
    }
}
