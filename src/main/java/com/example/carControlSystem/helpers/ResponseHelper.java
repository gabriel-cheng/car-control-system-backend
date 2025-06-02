package com.example.carControlSystem.helpers;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carControlSystem.exceptions.ResourceNotFoundException;

public class ResponseHelper {
    
    public static <T> ResponseEntity<List<T>> getListEntity(Supplier<List<T>> supplier, String contextName) {
        List<T> list = supplier.get();

        return ResponseEntity.status(HttpStatus.OK)
            .body(list);
    }

    public static <T, ID> ResponseEntity<T> getOneEntity(
        ID id,
        Function<ID, Optional<T>> findByIdFunction,
        String entityName
    ) throws ResourceNotFoundException {
        T entity = findByIdFunction.apply(id)
            .orElseThrow(() -> new ResourceNotFoundException(entityName + " " + id + " not found!"));

        return ResponseEntity.status(HttpStatus.OK)
            .body(entity);
    }

    public static <E> ResponseEntity<String> deleteEntity(
        Supplier<Optional<E>> findEntityById,
        Consumer<E> deleteFunction,
        String entityName,
        String id
    ) throws ResourceNotFoundException {
        E entity = findEntityById.get()
            .orElseThrow(() -> new ResourceNotFoundException(entityName + " " + id + " not found!"));
        
        deleteFunction.accept(entity);

        return ResponseEntity.status(HttpStatus.OK)
            .body(entityName + " deleted successfully!");
    }

}
