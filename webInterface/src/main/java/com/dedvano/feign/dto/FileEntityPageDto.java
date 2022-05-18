package com.dedvano.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FileEntityPageDto {
    private List<FileEntityDto> fileEntityList;
    private int currentPage;
    private int totalPages;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
