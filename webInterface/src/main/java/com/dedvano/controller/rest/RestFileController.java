package com.dedvano.controller.rest;

import com.dedvano.feign.FileServerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
public class RestFileController {

    private final FileServerClient fileServerClient;

    @DeleteMapping("/file/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") BigInteger id) {
        fileServerClient.deleteFile(id);
        return ResponseEntity.ok(null);
    }
}
