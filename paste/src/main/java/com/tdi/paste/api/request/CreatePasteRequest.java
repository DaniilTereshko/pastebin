package com.tdi.paste.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "A request to save a paste and obtain a link to it.")
@Getter
@Setter
public class CreatePasteRequest {

    @Schema(
            description = "Paste title",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "My paste"
    )
    private String title;

    @Schema(
            description = "Paste summary",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "This is my first paste in this app."
    )
    private String summary;

    @Schema(
            description = "Paste text",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "The paste text is... very large."
    )
    private String text;

    @Schema(
            description = "TTL as epoch timestamp",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Long expirationDate;
}