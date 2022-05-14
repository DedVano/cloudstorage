package fileserver.repository;

import fileserver.model.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, BigInteger> {

    List<FileEntity> findAllByOwnerId(Long ownerId);
    Page<FileEntity> findAllByOwnerId(Long ownerId, Pageable pageable);
    Page<FileEntity> findAllByCreationDateTime(Timestamp timestamp, Pageable pageable);
}
