package com.mhkim.tms.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhkim.tms.controller.v1.qna.dto.QnaDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QnaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void 게시글_등록() throws Exception {
        QnaDto.Add param = new QnaDto.Add();
        param.setTitle("title");
        param.setContent("content");

        mockMvc.perform(post("/api/v1/qna/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    void 게시글_전체_조회() throws Exception {
        mockMvc.perform(get("/api/v1/qnas")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void 게시글_단건_조회() throws Exception {
        mockMvc.perform(get("/api/v1/qna/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void 게시글_수정() throws Exception {
        QnaDto.Mod param = new QnaDto.Mod();
        param.setTitle("updatedtitle");
        param.setContent("updatedcontent");

        mockMvc.perform(put("/api/v1/qna/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void 게시글_삭제() throws Exception {
        mockMvc.perform(delete("/api/v1/qna/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
