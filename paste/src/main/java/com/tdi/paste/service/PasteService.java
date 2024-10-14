package com.tdi.paste.service;

import com.tdi.paste.api.dto.PasteLinkDTO;
import com.tdi.paste.api.request.CreatePasteRequest;

/**
 * An interface providing business logic for pastes.
 */
public interface PasteService {

    /**
     * A method for saving a paste {@link com.tdi.paste.model.Paste}. The text is stored in a blob storage,
     * and a link to it is saved in the database. The method provides the user
     * with a special short link, which can be used to access the text
     * and share it with other users. Additionally, information about
     * the link's TTL (Time to Live) is provided.
     * @param request {@link CreatePasteRequest}.
     * @return A {@link PasteLinkDTO} containing a link and TTL.
     */
    PasteLinkDTO save(CreatePasteRequest request);

    /**
     * Method for retrieving a paste {@link PasteLinkDTO}.
     * @param id Paste identifier.
     * @return A {@link PasteLinkDTO} containing a link and TTL.
     */
    PasteLinkDTO get(int id);
}