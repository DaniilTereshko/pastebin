package com.tdi.paste.repository.api;

import com.tdi.paste.model.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A repository for accessing entity {@link Paste} stored in the database.
 */
@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {
    Optional<Paste> findByLinkHash(String linkHash);
}