package com.mycompany.notepad.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "canvasObj")
public class CanvasObjectEntity {

    @Id
    private String id;
    private Object value;

}