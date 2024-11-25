package com.tdi.paste.service.api;

import com.tdi.paste.api.dto.PageDTO;
import com.tdi.paste.api.dto.PasteDTO;
import com.tdi.paste.api.dto.PasteLinkDTO;
import com.tdi.paste.api.dto.PasteReferenceDTO;
import com.tdi.paste.api.request.CreatePasteRequest;

/**
 * An interface providing business logic for pastes.
 *
 * @author Daniil Tereshko
 */
public interface PasteService {
    /**
     * Saves a paste by storing the text in blob storage and its metadata in the database.
     * This method generates a short link for the user, which can be shared to access the paste.
     *
     * @param request {@link CreatePasteRequest}.
     * @return {@link PasteLinkDTO}.
     */
    PasteLinkDTO save(CreatePasteRequest request);

    /**
     * Retrieving a paste by its hash.
     *
     * @param linkHash the hash of the paste link.
     * @return а {@link PasteDTO} containing the paste data.
     */
    PasteDTO get(String linkHash);

    /**
     * Retrieves a paginated list of paste references.
     *
     * @param page the page number to retrieve.
     * @param size the number of items per page.
     * @return a {@link PageDTO} containing a list of {@link PasteReferenceDTO} objects.
     */
    PageDTO<PasteReferenceDTO> getAll(int page, int size);
}