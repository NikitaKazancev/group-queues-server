package ru.rg.groupqueuesserver.subjects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectFullData {
    private Long id;
    private String name;
    private List<SubjectStudentFullData> students;
}
