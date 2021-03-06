package com.dedvano.service;

import com.dedvano.dto.FileEntityDto;
import com.dedvano.dto.FileEntityPageDto;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.List;

public interface FileEntityService {

    List<FileEntityDto> findAll();

    List<FileEntityDto> findAllByOwnerId(Long ownerId);

    FileEntityPageDto getPageWithFoundByOwnerId(Long ownerId, Pageable pageable);

    Resource getFileById(BigInteger fileId);

    FileEntityDto getFileEntityById(BigInteger fileId);

    FileEntityDto saveNewFile(MultipartFile newFile, Long ownerId);

    FileEntityDto editFileEntity(@Valid FileEntityDto fileEntity);

    void deleteFileAndEntityById(@NotEmpty BigInteger fileId);
}
