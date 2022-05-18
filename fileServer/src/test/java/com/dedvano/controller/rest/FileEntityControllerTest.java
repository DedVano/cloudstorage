package com.dedvano.controller.rest;

import com.dedvano.dto.FileEntityPageDto;
import com.dedvano.service.FileEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;

@DisplayName("REST-контроллер для работы с сущностями файлов должен ")
@WebMvcTest(FileEntityController.class)
public class FileEntityControllerTest {

    private static final String FILES_URI = "/api/files";

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private FileEntityService fileEntityService;

    @BeforeEach
    public void init() {
        Mockito.when(fileEntityService.getPageWithFoundByOwnerId(any(), any())).thenReturn(
                new FileEntityPageDto(Collections.emptyList(), -1, -1, true, true)
        );
    }

    @Test
    @DisplayName("уметь получать список файлов")
    public void getFiles() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(FILES_URI).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
    }
}
