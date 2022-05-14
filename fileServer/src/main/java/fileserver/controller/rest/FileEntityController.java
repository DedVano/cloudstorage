package fileserver.controller.rest;

import fileserver.dto.FileEntityDto;
import fileserver.dto.FileEntityPageDto;
import fileserver.service.FileEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileEntityController {

    private final FileEntityService fileEntityService;

    @GetMapping
    public FileEntityPageDto getPageOfFilesList(@PageableDefault(value = 15) Pageable pageable) {
        return fileEntityService.getPageWithFoundByOwnerId(null, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileEntityDto newFile(@RequestParam MultipartFile newFile) {
        return fileEntityService.saveNewFile(newFile);
    }

    @PutMapping
    public FileEntityDto editFile(@RequestBody FileEntityDto updatedEntity) {
        return fileEntityService.editFileEntity(updatedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable BigInteger id) {
        FileEntityDto fileEntity = fileEntityService.getFileEntityById(id);
        Resource resource = fileEntityService.getFileById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileEntity.getName() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable BigInteger id) {
        fileEntityService.deleteFileAndEntityById(id);
    }
}
