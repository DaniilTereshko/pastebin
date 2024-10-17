package com.tdi.paste.service;

import com.tdi.paste.api.dto.PasteLinkDTO;
import com.tdi.paste.api.request.CreatePasteRequest;
import com.tdi.paste.model.Paste;
import com.tdi.paste.model.PasteAudit;
import com.tdi.paste.repository.PasteRepository;
import com.tdi.paste.service.api.PasteService;
import com.tdi.paste.service.api.StorageService;
import com.tdi.paste.service.api.TemporaryStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Transactional
@AllArgsConstructor
public class PasteServiceImpl implements PasteService {

    public static final String PASTE_NOT_FOUND_BY_ID_MESSAGE = "Paste with id {%d} not found";
    private final TemporaryStorageService temporaryStorageService;
    private final StorageService storageService;
    private final PasteRepository repository;

    @Override
    public PasteLinkDTO save(CreatePasteRequest request) {
        var file = temporaryStorageService.saveToDirectory(request);

        uploadPaste(file);

        var paste = new Paste();
        var expirationDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(request.getExpirationDate()), ZoneOffset.UTC);//TODO date
        paste.setExpirationDate(expirationDate);
        paste.setAudit(PasteAudit.onCreate(paste));
        var savedPaste = repository.save(paste);

        return new PasteLinkDTO(file.getName(), savedPaste.getExpirationDate());
    }

    @Override
    @Transactional(readOnly = true)
    public PasteLinkDTO get(int id) {
        var paste = getOrThrow(id);
        return new PasteLinkDTO(paste.getFileName(), paste.getExpirationDate());
    }

    private Paste getOrThrow(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(PASTE_NOT_FOUND_BY_ID_MESSAGE, id)));
    }

    private void uploadPaste(File temporaryPasteFile) {
        try {
            storageService.uploadPaste(temporaryPasteFile);
        } finally {
            temporaryStorageService.deleteFile(temporaryPasteFile);
        }
    }
}