package notepad.example.canvas.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "notepad")
public class Notepad {
    @Id
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
