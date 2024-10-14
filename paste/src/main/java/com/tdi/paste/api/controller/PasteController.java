package com.tdi.paste.api.controller;

import com.tdi.paste.api.dto.PasteLinkDTO;
import com.tdi.paste.api.request.CreatePasteRequest;
import com.tdi.paste.service.PasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Paste")
@RestController
@RequestMapping(value = "/api/v1/paste", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PasteController {//TODO create a separete entity LINK with TTL and add to paste column title

    private final PasteService service;

    @Operation(summary = "Saving a paste and obtaining a link to it.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PasteLinkDTO create(@RequestBody CreatePasteRequest request) {
        return service.save(request);
    }

    @Operation(summary = "Retrieving the paste")
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public PasteLinkDTO get(@Parameter(description = "ID") @PathVariable("id") int id) {
        return service.get(id);
    }
}