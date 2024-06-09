package com.fsknso.competition.core.common;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity extends BaseEntity {
    @CreatedBy
    @NotNull
    @Column(name = "createdBy", nullable = false)
    private String createdBy = (SecurityContextHolder.getContext() != null &&
            SecurityContextHolder.getContext().getAuthentication() != null) ?
            SecurityContextHolder.getContext().getAuthentication().getName() : "anonimus";

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate = LocalDateTime.now(ZoneId.systemDefault());

    @LastModifiedBy
    @Column(name = "lastModifiedBy")
    private String lastModifiedBy = (SecurityContextHolder.getContext() != null &&
            SecurityContextHolder.getContext().getAuthentication() != null) ?
            SecurityContextHolder.getContext().getAuthentication().getName() : createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    // PrePersist and PreUpdate methods to set createdDate and lastModifiedDate
    @PrePersist
    protected void onCreated() {
        lastModifiedDate = LocalDateTime.now(ZoneId.systemDefault());
        createdDate = LocalDateTime.now(ZoneId.systemDefault());
    }

    @PreUpdate
    protected void onUpdated() {
        lastModifiedDate = LocalDateTime.now(ZoneId.systemDefault());
    }

    @PreRemove
    protected void onDeleted() {
        lastModifiedDate = LocalDateTime.now(ZoneId.systemDefault());
    }
}