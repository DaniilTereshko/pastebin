package com.tdi.paste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The entity of a system object representing the audit of a block of text.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PasteAudit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private Paste paste;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private int createdBy;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @LastModifiedBy
    private int updatedBy;

    public static PasteAudit onCreate(Paste paste) {
        var audit = new PasteAudit();
        audit.setPaste(paste);
        return audit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasteAudit pasteAudit = (PasteAudit) o;
        return getPaste().equals(pasteAudit.getPaste());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}