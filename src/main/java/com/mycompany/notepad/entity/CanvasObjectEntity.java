package com.mycompany.notepad.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "canvasObj")
public class CanvasObjectEntity {

    @Id
    private String id;
    private Object value;

}