package com.tdi.paste.logic;

import com.tdi.paste.api.dto.PageDTO;
import com.tdi.paste.api.dto.PasteDTO;
import com.tdi.paste.api.dto.PasteReferenceDTO;
import com.tdi.paste.model.Paste;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PasteMapper {
    private final static String PASTE_LINK_TEMPLATE = "/paste/%s";
    private final ModelMapper mapper;

    public PasteDTO toPasteDto(Paste paste, String text) {
        var dto = mapper.map(paste, PasteDTO.class);

        dto.setText(text);
        dto.setExpires(paste.getExpirationDate());
        dto.setAdded(paste.getAudit().getCreatedAt());

        return dto;
    }

    public PageDTO<PasteReferenceDTO> toPageDto(Page<Paste> pastePage) {
        var dto = new PageDTO<PasteReferenceDTO>();

        dto.setSize(pastePage.getSize());
        dto.setNumber(pastePage.getNumber());
        dto.setLast(pastePage.isLast());
        dto.setFirst(pastePage.isFirst());
        dto.setTotalPages(pastePage.getTotalPages());
        dto.setTotalElements(pastePage.getTotalElements());
        dto.setNumberOfElements(pastePage.getNumberOfElements());
        dto.setContent(toPasteReferenceDto(pastePage.getContent()));

        return dto;
    }

    private PasteReferenceDTO toPasteReferenceDto(Paste paste) {
        var dto = mapper.map(paste, PasteReferenceDTO.class);

        dto.setExpires(paste.getExpirationDate());
        dto.setAdded(paste.getAudit().getCreatedAt());
        dto.setLink(String.format(PASTE_LINK_TEMPLATE, paste.getLinkHash()));

        return dto;
    }

    private List<PasteReferenceDTO> toPasteReferenceDto(List<Paste> pastes) {
        return pastes.stream()
                .map(this::toPasteReferenceDto)
                .toList();
    }
}