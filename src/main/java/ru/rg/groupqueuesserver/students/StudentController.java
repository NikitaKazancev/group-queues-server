package ru.rg.groupqueuesserver.students;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rg.groupqueuesserver.general.ControllerFunctions;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<StudentEntity> findById(@PathVariable Long id) {
        return functions.findBy(id, studentService::findById);
    }
    @PostMapping
    public ResponseEntity<StudentEntity> save(
            @RequestBody StudentEntity student
    ) {
        return ResponseEntity.ok(studentService.save(student));
    }
    @PutMapping("{id}")
    public ResponseEntity<StudentEntity> change(
            @PathVariable Long id,
            @RequestBody StudentEntity student
    ) {
        return functions.change(id, student, studentService::change);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
