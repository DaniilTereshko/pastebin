package com.tdi.paste.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "A DTO that represents a link to a paste with its TTL.")
@Getter
@Setter
@AllArgsConstructor
public class PasteLinkDTO {

    @Schema(description = "Link to a paste.")
    private String link;

    @Schema(description = "Paste's TTL")
    private LocalDateTime expirationDate;
}