package com.mhkim.tms.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhkim.tms.board.controller.dto.BoardAddDto;
import com.mhkim.tms.board.controller.dto.BoardUpdateDto;
import com.mhkim.tms.board.domain.Board;
import com.mhkim.tms.board.repository.BoardRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    void setUp(){
        IntStream.range(1, 10).forEach(i -> {
            Board board = Board.builder()
                    .userName("mhkim")
                    .title("title "+i)
                    .content("content "+i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    @Order(1)
    void 게시글_등록() throws Exception {
        BoardAddDto param = new BoardAddDto();
        param.setUserName("addmhkim");
        param.setTitle("title");
        param.setContent("content");
        
        mockMvc.perform(post("/api/board/add")
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
        mockMvc.perform(get("/api/board/list")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void 게시글_단건_조회() throws Exception {
        mockMvc.perform(get("/api/board/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void 게시글_수정() throws Exception{
        BoardUpdateDto param = new BoardUpdateDto();
        param.setTitle("updatedtitle");
        param.setContent("updatedcontent");

        mockMvc.perform(put("/api/board/3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void 게시글_삭제() throws Exception{
        mockMvc.perform(delete("/api/board/5")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
}
