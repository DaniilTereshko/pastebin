package com.tdi.paste.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Schema(description = "Base paste DTO.")
@Getter
@Setter
@NoArgsConstructor
public abstract class PasteBaseDTO {
    @Schema(description = "Unique identifier for the paste.")
    private int id;

    @Schema(description = "Title of the paste.")
    private String title;

    @Schema(description = "Short summary of the paste content.")
    private String summary;

    @Schema(description = "Expiration timestamp for the paste in milliseconds since epoch.")
    private Long expires;

    @Schema(description = "Timestamp when the paste was added in milliseconds since epoch.")
    private long added;

    public void setExpires(LocalDateTime date) {
        expires = date != null ? date.toInstant(ZoneOffset.UTC).toEpochMilli() : null;
    }

    public void setAdded(LocalDateTime date) {
        added = date.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}