package com.tdi.paste.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The entity of a system object representing a block of text.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paste implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String summary;

    @Column(nullable = false, updatable = false)
    private String bucket;

    @Column(nullable = false, unique = true, updatable = false)
    private String fileName;

    @Column(nullable = false, unique = true, updatable = false)//TODO nullable = false
    private String linkHash;

    private LocalDateTime expirationDate;

    @OneToOne(
            mappedBy = "paste",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private PasteAudit audit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paste paste = (Paste) o;
        return getId().equals(paste.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}