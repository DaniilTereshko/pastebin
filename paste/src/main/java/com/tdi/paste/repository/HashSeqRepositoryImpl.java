package com.tdi.paste.repository;

import com.tdi.paste.repository.api.HashSeqRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class HashSeqRepositoryImpl implements HashSeqRepository {
    private static final String SELECT_NEXTVAL_LINK_HASH_SEQ = "SELECT nextval('link_hash_seq')";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer getNextLinkHashId() {
        return jdbcTemplate.queryForObject(SELECT_NEXTVAL_LINK_HASH_SEQ, Integer.class);
    }
}