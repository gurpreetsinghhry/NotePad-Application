package com.mycompany.notepad.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CanvasObjectModel {
    @Id
    @NotBlank
    private String id;
    private Object value;
}
