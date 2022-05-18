package com.dedvano.controller;

import com.dedvano.feign.FileServerClient;
import com.dedvano.feign.dto.FileEntityPageDto;
import com.dedvano.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RequiredArgsConstructor
@Controller
@RequestMapping("/files")
public class FilesListController {

    private final FileServerClient fileServerClient;

    @GetMapping
    public String listFiles(Model model,
                            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer page,
                            @Positive @RequestParam(required = false, defaultValue = "15") Integer size) {
        Long userId = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        FileEntityPageDto pageOfFilesList = fileServerClient.getPageOfFilesList(userId, page, size);
        model.addAttribute("files", pageOfFilesList);
        model.addAttribute("size", size);
        return "filesList/files";
    }

    @PostMapping
    public String newFile(@RequestParam MultipartFile newFile) {
        Long userId = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        fileServerClient.newFile(userId, newFile);
        return "redirect:/files";
    }
}

