package com.dedvano.repository;

import com.dedvano.domain.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, BigInteger> {

    List<FileEntity> findAllByOwnerId(Long ownerId);
    Page<FileEntity> findAllByOwnerId(Long ownerId, Pageable pageable);
    Page<FileEntity> findAllByOwnerIdOrderByName(Long ownerId, Pageable pageable);
}
