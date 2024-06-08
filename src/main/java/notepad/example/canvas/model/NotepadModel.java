package notepad.example.canvas.model;

import lombok.Data;

import java.util.List;

@Data
public class NotepadModel {
    private String id;
    private String projectName;
    private String createdTime;
    private String updatedTime;
    private String author;
    private List<CanvasObject> canvasObj;

    @Data
    public static class CanvasObject {
        private String objectType;
        private Double xCoordinate;
        private Double yCoordinate;
        private Double height;
        private Double width;
    }
}
