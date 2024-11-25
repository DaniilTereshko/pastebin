package com.tdi.paste.api.controller;

import com.tdi.paste.api.dto.PageDTO;
import com.tdi.paste.api.dto.PasteDTO;
import com.tdi.paste.api.dto.PasteLinkDTO;
import com.tdi.paste.api.dto.PasteReferenceDTO;
import com.tdi.paste.api.request.CreatePasteRequest;
import com.tdi.paste.service.api.PasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Paste")
@RestController
@RequestMapping(value = "/api/v1/pastes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PasteController {
    private final PasteService pasteService;

    @Operation(summary = "Saving a paste and obtaining a link to it.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PasteLinkDTO create(@RequestBody CreatePasteRequest request) {//TODO response
        return pasteService.save(request);
    }

    @Operation(summary = "Retrieving the paste.")
    @GetMapping("/{linkHash}")
    public PasteDTO get(@Parameter(description = "Link hash") @PathVariable("linkHash") String linkHash) {
        return pasteService.get(linkHash);
    }

    @Operation(summary = "Retrieving paginated pastes.")
    @GetMapping
    public PageDTO<PasteReferenceDTO> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return pasteService.getAll(page, size);
    }
}