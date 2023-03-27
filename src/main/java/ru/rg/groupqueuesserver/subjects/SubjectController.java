package ru.rg.groupqueuesserver.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rg.groupqueuesserver.general.ControllerFunctions;
import ru.rg.groupqueuesserver.subjects.dto.SubjectFullData;
import ru.rg.groupqueuesserver.subjects.dto.SubjectStudent;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<List<SubjectEntity>> findAll() {
        return ResponseEntity.ok(subjectService.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<SubjectFullData> findById(@PathVariable Long id) {
        return functions.findBy(id, subjectService::findById);
    }
    @PostMapping
    public ResponseEntity<SubjectEntity> save(@RequestBody SubjectEntity subject) {
        return ResponseEntity.ok(subjectService.save(subject));
    }
    @PutMapping("{id}")
    public ResponseEntity<SubjectEntity> change(
            @PathVariable Long id,
            @RequestBody SubjectEntity subject
    ) {
        return functions.change(id, subject, subjectService::change);
    }
    @PutMapping("{subjectId}/students/{studentId}")
    public ResponseEntity<SubjectEntity> changeStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId,
            @RequestBody SubjectStudent student
    ) {
        SubjectEntity responseObj = subjectService.changeStudent(subjectId, studentId, student);
        if (responseObj != null) {
            return ResponseEntity.ok(responseObj);
        }

        return ResponseEntity.status(404).body(null);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        subjectService.deleteById(id);
    }
}
