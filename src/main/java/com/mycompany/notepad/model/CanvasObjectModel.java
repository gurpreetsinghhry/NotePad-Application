package com.mycompany.notepad.model;

import org.springframework.data.annotation.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CanvasObjectModel {
    @Id
    @Schema(hidden=true)
    private String id;
    private Object value;
}
