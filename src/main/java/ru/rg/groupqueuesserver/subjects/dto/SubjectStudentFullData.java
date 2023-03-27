package ru.rg.groupqueuesserver.subjects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rg.groupqueuesserver.students.StudentEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentFullData {
    private StudentEntity student;
    private String status;
}
