package com.dedvano.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
public class FileEntityDto {

    private BigInteger id;

    @NotEmpty
    private String name;

    private Long sizeKb;

    private Timestamp creationDateTime;

    private Long ownerId;
}
