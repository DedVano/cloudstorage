package com.dedvano.controller;

import com.dedvano.feign.FileServerClient;
import com.dedvano.feign.dto.FileEntityDto;
import com.dedvano.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileServerClient fileServerClient;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadById(@PathVariable BigInteger id) throws IllegalAccessException {
        if (checkRights(fileServerClient.getFileEntityById(id))) {
            return fileServerClient.downloadById(id);
        } else {
            throw new IllegalAccessException("You do not have rights to access this file");
        }
    }

    @GetMapping("/edit")
    public String renameFile(@RequestParam("id") BigInteger fileId, Model model) throws IllegalAccessException {
        FileEntityDto fileEntity = fileServerClient.getFileEntityById(fileId);
        if(checkRights(fileEntity)) {
            model.addAttribute("file", fileEntity);
            return "filesList/fileEdit";
        } else {
            throw new IllegalAccessException("You do not have rights to access this file");
        }
    }

    @PostMapping("/save")
    public String saveFileEntity(FileEntityDto fileEntity) throws IllegalAccessException {
        FileEntityDto oldFileEntity = fileServerClient.getFileEntityById(fileEntity.getId());
        if (checkRights(oldFileEntity)) {
            fileEntity.setSizeKb(oldFileEntity.getSizeKb());
            fileEntity.setCreationDateTime(oldFileEntity.getCreationDateTime());
            fileEntity.setOwnerId(oldFileEntity.getOwnerId());
            fileServerClient.saveFileEntity(fileEntity);
            return "redirect:/files";
        } else {
            throw new IllegalAccessException("You do not have rights to access this file");
        }
    }

    boolean checkRights(FileEntityDto fileEntity) {
        Long userId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        return fileEntity.getOwnerId().equals(userId);
    }
}
