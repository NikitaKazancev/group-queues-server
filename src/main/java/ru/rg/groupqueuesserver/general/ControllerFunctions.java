package ru.rg.groupqueuesserver.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.rg.groupqueuesserver.general.types.Func2Args;
import ru.rg.groupqueuesserver.subjects.SubjectEntity;
import ru.rg.groupqueuesserver.subjects.dto.SubjectStudent;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ControllerFunctions {
    public <ObjectType, PropType> ResponseEntity<ObjectType> findBy(
            PropType property,
            Function<PropType, ObjectType> findFunction
    ) {
        ObjectType object = findFunction.apply(property);
        if (object != null) {
            return ResponseEntity.ok(object);
        }

        return ResponseEntity.status(404).body(null);
    }
    public <ObjectType, PropType> ResponseEntity<ObjectType> change(
            PropType property,
            ObjectType object,
            Func2Args<PropType, ObjectType, ObjectType> changeFunction
    ) {
        ObjectType responseObj = changeFunction.apply(property, object);
        if (responseObj != null) {
            return ResponseEntity.ok(responseObj);
        }

        return ResponseEntity.status(404).body(null);
    }
}
