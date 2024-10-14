package com.tdi.paste.service.impl;

import com.tdi.paste.api.dto.PasteLinkDTO;
import com.tdi.paste.api.request.CreatePasteRequest;
import com.tdi.paste.model.Paste;
import com.tdi.paste.model.PasteAudit;
import com.tdi.paste.repository.PasteRepository;
import com.tdi.paste.service.PasteService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Transactional
@AllArgsConstructor
public class PasteServiceImpl implements PasteService {

    private final PasteRepository repository;

    @Override
    public PasteLinkDTO save(CreatePasteRequest request) {
        Paste paste = new Paste();
        LocalDateTime expirationDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(request.getExpirationDate()), ZoneOffset.UTC);//TODO date
        paste.setExpirationDate(expirationDate);
        paste.setBlobStorageLink(RandomStringUtils.randomAlphabetic(10));//TODO заменить при реализации blob storage
        paste.setAudit(PasteAudit.onCreate(paste));
        Paste save = repository.save(paste);
        return new PasteLinkDTO(save.getBlobStorageLink(), save.getExpirationDate());
    }

    @Override
    @Transactional(readOnly = true)
    public PasteLinkDTO get(int id) {
        Paste paste = getOrThrow(id);
        return new PasteLinkDTO(paste.getBlobStorageLink(), paste.getExpirationDate());
    }

    private Paste getOrThrow(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Paste with id {%d} not found", id)));
    }
}