package com.mycompany.notepad.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.mycompany.notepad.Util.annotations.AlphaSpace;
import com.mycompany.notepad.entity.CanvasObjectEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectModel {
    @Id
    private String id;
    @Schema(hidden=true)
    private String projectName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    @NotBlank
    @AlphaSpace
    private String author;
    private List<CanvasObjectEntity> canvasObj = new ArrayList<>();
}