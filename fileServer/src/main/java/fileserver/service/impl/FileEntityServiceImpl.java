package fileserver.service.impl;

import fileserver.dto.FileEntityDto;
import fileserver.dto.FileEntityPageDto;
import fileserver.mapper.FileEntityMapper;
import fileserver.model.FileEntity;
import fileserver.repository.FileRepository;
import fileserver.service.FileEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileEntityServiceImpl implements FileEntityService {

    private final Path path = Paths.get("d:/fileStorage");
    private final FileRepository fileRepository;
    private final FileEntityMapper fileEntityMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FileEntityDto> findAll() {
        return fileEntityMapper.toDtos(fileRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileEntityDto> findAllByOwnerId(Long ownerId) {
        return fileEntityMapper.toDtos(fileRepository.findAllByOwnerId(ownerId));
    }

    @Override
    public FileEntityPageDto getPageWithFoundByOwnerId(Long ownerId, Pageable pageable) {
        Page<FileEntity> currentPage = fileRepository.findAllByOwnerId(ownerId, pageable);
        return new FileEntityPageDto(fileEntityMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious());
    }

    @Override
    public Resource getFileById(BigInteger fileId) {
        try {
            return new UrlResource(this.path.resolve(fileId + ".fst").toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + fileId + ".fst");
        }
    }

    @Override
    public FileEntityDto getFileEntityById(BigInteger fileId) {
        Optional<FileEntity> fileEntity = fileRepository.findById(fileId);
        return fileEntity.isPresent() ? fileEntityMapper.toDto(fileEntity.get()) : null;
    }

    @Override
    public FileEntityDto saveNewFile(MultipartFile newFile) {
        FileEntityDto newFileEntityDto = new FileEntityDto();
        newFileEntityDto.setName(newFile.getOriginalFilename());
        newFileEntityDto.setSizeKb(newFile.getSize() / 1024);
        newFileEntityDto = fileEntityMapper.toDto(fileRepository.save(fileEntityMapper.toEntity(newFileEntityDto)));
        try (InputStream inputStream = newFile.getInputStream()) {
            Files.copy(inputStream, this.path.resolve(newFileEntityDto.getId() + ".fst"));
            return newFileEntityDto;
        } catch (IOException e) {
            deleteFileAndEntityById(newFileEntityDto.getId());
            throw new RuntimeException("Could not store the file. Error:" + e.getMessage());
        }
    }

    @Override
    public FileEntityDto editFileEntity(FileEntityDto fileEntity) {
        return fileEntityMapper.toDto(fileRepository.save(fileEntityMapper.toEntity(fileEntity)));
    }

    @Override
    public void deleteFileAndEntityById(BigInteger fileId) {
        fileRepository.deleteById(fileId);
    }
}
