package com.tdi.paste.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "A request to save a paste and obtain a link to it.")
@Getter
@Setter
public class CreatePasteRequest {

    @Schema(description = "Paste text")
    private String text;

    @Schema(description = "TTL as epoch timestamp")
    private Long expirationDate;
}