package com.dedvano.feign;

import com.dedvano.feign.dto.FileEntityDto;
import com.dedvano.feign.dto.FileEntityPageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

@FeignClient(name = "fileserver-storage")
public interface FileServerClient {

    @GetMapping("/api/files")
    FileEntityPageDto getPageOfFilesList(@RequestParam Long ownerId, @RequestParam Integer page, @RequestParam Integer size);

    @PostMapping(value = "/api/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void newFile(@RequestParam Long ownerId, @RequestPart MultipartFile newFile);

    @GetMapping("/api/files/download/{id}")
    ResponseEntity<Resource> downloadById(@PathVariable BigInteger id);

    @GetMapping("/api/files/{id}")
    FileEntityDto getFileEntityById(@PathVariable BigInteger id);

    @PutMapping("/api/files")
    FileEntityDto saveFileEntity(@RequestBody FileEntityDto updatedEntity);

    @DeleteMapping("/api/files/{id}")
    void deleteFile(@PathVariable BigInteger id);
}
