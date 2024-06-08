package notepad.example.canvas.service;

import notepad.example.canvas.model.NotepadModel;

import java.util.List;

public interface NotepadService {
    NotepadModel getNotepadById(String id);
    List<NotepadModel> getAllNotepads();
    NotepadModel createNotepad(NotepadModel notepadModel);
    NotepadModel updateNotepad(String id, NotepadModel notepadModel);
}
