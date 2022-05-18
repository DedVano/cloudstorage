package com.dedvano.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FILE")
public class FileEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotEmpty
    private String name;

    @Column(name = "size_kb", columnDefinition = "int unsigned")
    private Long sizeKb;

    @CreationTimestamp
    @Column(name = "creation_date_time")
    private Timestamp creationDateTime;

    @Column(name = "owner_id", columnDefinition = "int unsigned")
    private Long ownerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileEntity that = (FileEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
