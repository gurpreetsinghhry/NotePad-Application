package notepad.example.canvas.controller;

import notepad.example.canvas.model.NotepadModel;
import notepad.example.canvas.service.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notepad")
public class NotepadController {

    @Autowired
    private NotepadService notepadService;

    @GetMapping("/{id}")
    public NotepadModel getNotepadById(@PathVariable String id,
                                       @RequestHeader String user_name,
                                       @RequestHeader String password) {
        // Validate user_name and password
        // For now, this can be a simple check
        if (isValidUser(user_name, password)) {
            return notepadService.getNotepadById(id);
        }
        return null;
    }

    @GetMapping
    public List<NotepadModel> getAllNotepads(@RequestHeader String user_name,
                                             @RequestHeader String password) {
        // Validate user_name and password
        if (isValidUser(user_name, password)) {
            return notepadService.getAllNotepads();
        }
        return null;
    }

    @PostMapping
    public NotepadModel createNotepad(@RequestBody NotepadModel notepadModel,
                                      @RequestHeader String user_name,
                                      @RequestHeader String password) {
        // Validate user_name and password
        if (isValidUser(user_name, password)) {
            return notepadService.createNotepad(notepadModel);
        }
        return null;
    }

    @PutMapping("/{id}")
    public NotepadModel updateNotepad(@PathVariable String id,
                                      @RequestBody NotepadModel notepadModel,
                                      @RequestHeader String user_name,
                                      @RequestHeader String password) {
        // Validate user_name and password
        if (isValidUser(user_name, password)) {
            return notepadService.updateNotepad(id, notepadModel);
        }
        return null;
    }

    private boolean isValidUser(String user_name, String password) {
        // Simple validation logic for now
        return "guest_user".equals(user_name) && "guest_password".equals(password);
    }
}
