package notepad.example.canvas.repository;

import notepad.example.canvas.entity.Notepad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotepadRepository extends MongoRepository<Notepad, String> {
}
