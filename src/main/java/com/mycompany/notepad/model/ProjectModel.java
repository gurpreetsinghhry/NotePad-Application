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
    @Schema(hidden=true)
    private String id;
<<<<<<< HEAD
    @Schema(eample="Name of the project.")
=======
    @Schema(hidden=true)
>>>>>>> 7ce6bf0ec4c11c459fc7e9e52428f8cb5ebf1779
    private String projectName;
    @Schema(hidden=true)
    private LocalDateTime createdTime;
    @Schema(hidden=true)
    private LocalDateTime updatedTime;
<<<<<<< HEAD
    @Schema(example="Author of the project.")
=======
    @NotBlank
    @AlphaSpace
>>>>>>> ff27d0ec514d69281a95a81f5dbaa4eef4d1bb54
    private String author;
    @Schema(hidden=true)
    private List<CanvasObjectEntity> canvasObj = new ArrayList<>();
}