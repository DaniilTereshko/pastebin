package com.tdi.paste.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO representing a paste object with detailed information.")
@Getter
@Setter
@NoArgsConstructor
public class PasteDTO extends PasteBaseDTO {
    @Schema(description = "Full text content of the paste.")
    private String text;
}