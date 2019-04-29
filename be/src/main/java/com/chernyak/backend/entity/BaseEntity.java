package com.chernyak.backend.entity;



import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    private Long created;

    @Column(name = "updated", nullable = false)
    private Long updated;

    @PrePersist
    public void prePersist() {
        this.created = System.currentTimeMillis();
        this.updated = System.currentTimeMillis();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
       this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }
}
