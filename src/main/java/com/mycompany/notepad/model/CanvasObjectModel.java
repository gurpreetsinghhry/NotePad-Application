package com.mycompany.notepad.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CanvasObjectModel {
    @Id
    private String id;
    private String canvasobjectType;
    private double xCoordinate;
    private double yCoordinate;
    private double height;
    private double width;
}
