package com.tdi.paste.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO representing a paste reference object with link.")
@Getter
@Setter
@NoArgsConstructor
public class PasteReferenceDTO extends PasteBaseDTO {
    @Schema(description = "Link to the paste content.")
    private String link;
}