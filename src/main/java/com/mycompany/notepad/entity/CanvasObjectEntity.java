package com.mycompany.notepad.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CanvasObjectEntity {
    @Id
    private String id;
    private String canvasobjectType;
    private double xCoordinate;
    private double yCoordinate;
    private double height;
    private double width;
}