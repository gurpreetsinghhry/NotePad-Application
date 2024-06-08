package notepad.example.canvas.service.impl;

import notepad.example.canvas.entity.Notepad;
import notepad.example.canvas.model.NotepadModel;
import notepad.example.canvas.repository.NotepadRepository;
import notepad.example.canvas.service.NotepadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotepadServiceImpl implements NotepadService {

    @Autowired
    private NotepadRepository notepadRepository;

    @Override
    public NotepadModel getNotepadById(String id) {
        Optional<Notepad> notepadOptional = notepadRepository.findById(id);
        if (notepadOptional.isPresent()) {
            NotepadModel model = new NotepadModel();
            BeanUtils.copyProperties(notepadOptional.get(), model);
            return model;
        }
        return null;
    }

    @Override
    public List<NotepadModel> getAllNotepads() {
        return notepadRepository.findAll().stream()
                .map(entity -> {
                    NotepadModel model = new NotepadModel();
                    BeanUtils.copyProperties(entity, model);
                    return model;
                }).collect(Collectors.toList());
    }

    @Override
    public NotepadModel createNotepad(NotepadModel notepadModel) {
        Notepad entity = new Notepad();
        BeanUtils.copyProperties(notepadModel, entity);
        entity.setProjectName(notepadModel.getProjectName());
        entity.setCreatedTime(Instant.now().toString());
        entity.setUpdatedTime(Instant.now().toString());
        entity.setAuthor("for_now_guest_user");
        Notepad saved = notepadRepository.save(entity);
        BeanUtils.copyProperties(saved, notepadModel);
        return notepadModel;
    }

    @Override
    public NotepadModel updateNotepad(String id, NotepadModel notepadModel) {
        Optional<Notepad> notepadOptional = notepadRepository.findById(id);
        if (notepadOptional.isPresent()) {
            Notepad entity = notepadOptional.get();
            BeanUtils.copyProperties(notepadModel, entity, "id", "createdTime", "author");
            entity.setUpdatedTime(Instant.now().toString());
            Notepad saved = notepadRepository.save(entity);
            BeanUtils.copyProperties(saved, notepadModel);
            return notepadModel;
        }
        return null;
    }
}
