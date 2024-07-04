package com.talesfunmedia.forohubcompleto.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class TratadorDeErrores {

    //error 404 not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tatarError404() {
        return ResponseEntity.notFound().build();
    }

    //error 400 bad request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tatarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    // error 409 Conflict (foreign key constraint)
    @ExceptionHandler({SQLException.class, DataIntegrityViolationException.class})
    public ResponseEntity tratarErrorConstraintForeignKey(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se puede eliminar el registro porque tiene relaciones con otros datos.");
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }
}
