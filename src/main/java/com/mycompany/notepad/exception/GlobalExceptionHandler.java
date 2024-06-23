package com.mycompany.notepad.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> handleCanvasNotFoundException(ProjectNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(CanvasObjectNotFoundException.class)
    public ResponseEntity<?> handleCanvasNotFoundException(CanvasObjectNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
