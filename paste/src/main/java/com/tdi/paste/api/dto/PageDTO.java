package com.tdi.paste.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(description = "DTO representing paginated response data.")
@Getter @Setter
@NoArgsConstructor
public class PageDTO<C> {
    @Schema(description = "Current page number (zero-based).")
    private int number;

    @Schema(description = "Size of the page, indicating the number of elements per page.")
    private int size;

    @Schema(description = "Total number of pages available.")
    private int totalPages;

    @Schema(description = "Total number of elements across all pages.")
    private long totalElements;

    @Schema(description = "Indicates if this is the first page.")
    private boolean first;

    @Schema(description = "Number of elements in the current page.")
    private int numberOfElements;

    @Schema(description = "Indicates if this is the last page.")
    private boolean last;

    @Schema(description = "List of content elements in the current page.")
    private List<C> content;
}