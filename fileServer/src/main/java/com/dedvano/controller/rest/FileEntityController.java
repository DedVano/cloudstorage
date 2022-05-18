package com.dedvano.controller.rest;

import com.dedvano.dto.FileEntityDto;
import com.dedvano.dto.FileEntityPageDto;
import com.dedvano.service.FileEntityService;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileEntityController {

    private final FileEntityService fileEntityService;

    @GetMapping
    public FileEntityPageDto getPageOfFilesList(@RequestParam(required = false) Long ownerId, @PageableDefault(value = 15) Pageable pageable) {
        return fileEntityService.getPageWithFoundByOwnerId(ownerId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileEntityDto newFile(@RequestParam Long ownerId, @RequestPart MultipartFile newFile) {
        System.out.println(ownerId);
        return fileEntityService.saveNewFile(newFile, ownerId);
    }

    @PutMapping
    public FileEntityDto editFile(@RequestBody FileEntityDto updatedEntity) {
        return fileEntityService.editFileEntity(updatedEntity);
    }

    @GetMapping("/{id}")
    public FileEntityDto getFileEntity(@PathVariable BigInteger id) {
        return fileEntityService.getFileEntityById(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable BigInteger id) {
        FileEntityDto fileEntity = fileEntityService.getFileEntityById(id);
        Resource resource = fileEntityService.getFileById(id);
        try {
            String encodedName = URLEncoder.encode(fileEntity.getName(), StandardCharsets.UTF_8.toString())
                    .replace("+", "%20");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + encodedName + "\"")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable BigInteger id) {
        fileEntityService.deleteFileAndEntityById(id);
    }
}
