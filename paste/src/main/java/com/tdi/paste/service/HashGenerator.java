package com.tdi.paste.service;

import com.tdi.paste.repository.api.HashSeqRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Base64;

@Component
@AllArgsConstructor
public class HashGenerator {

    public static final int INT_BYTE_SIZE = 4;
    private final HashSeqRepository hashSeqRepository;

    public String generateHash() {
        Integer linkHashId = hashSeqRepository.getNextLinkHashId();
        return Base64.getEncoder()
                .encodeToString(ByteBuffer.allocate(INT_BYTE_SIZE)
                        .putInt(linkHashId)
                        .array());
    }
}