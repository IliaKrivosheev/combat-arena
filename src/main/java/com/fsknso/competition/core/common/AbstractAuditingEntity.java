package com.fsknso.competition.core.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity extends BaseEntity {

    @CreatedBy
    @NotNull
    @Column(name = "createdBy", nullable = false, length = 50)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "lastModifiedBy", length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    // PrePersist and PreUpdate methods to set createdDate and lastModifiedDate
    @PrePersist
    protected void onCreated() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated() {
        lastModifiedDate = LocalDateTime.now();
    }
}
