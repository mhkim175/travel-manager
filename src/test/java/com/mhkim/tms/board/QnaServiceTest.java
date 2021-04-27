package com.mhkim.tms.board;

import com.mhkim.tms.v1.board.entity.Qna;
import com.mhkim.tms.v1.board.service.QnaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QnaServiceTest {

    @Autowired
    private QnaService qnaService;

    private Qna qna;

    @BeforeAll
    void setUp() {
        Long qnaId = null;
        String userName = "mhkim";
        String title = "title";
        String content = "content";
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        qna = new Qna(qnaId, userName, title, content);
    }

    @Test
    @Order(1)
    void 게시글_등록() {
        Qna qnaResult = qnaService.addQna(qna.getUserName(), qna.getTitle(), qna.getContent());
        assertThat(qnaResult).isNotNull();
        assertThat(qnaResult.getUserName()).isEqualTo(qna.getUserName());
        assertThat(qnaResult.getTitle()).isEqualTo(qna.getTitle());
        assertThat(qnaResult.getContent()).isEqualTo(qna.getContent());
    }

    @Test
    @Order(2)
    void 게시글_전체_조회() {
        List<Qna> qnas = qnaService.getQnaList();
        assertThat(qnas).isNotNull().hasSize(1);
    }

    @Test
    @Order(3)
    void 게시글_단건_조회() {
        Qna qnaResult = qnaService.getQna(1L).orElse(null);
        assertThat(qnaResult).isNotNull();
    }

    @Test
    @Order(4)
    void 게시글_수정() {
        String title = "updatedtitle";
        String content = "updatedcontent";
        Qna qnaResult = qnaService.updateQna(1L, title, content);
        assertThat(qnaResult).isNotNull();
    }

    @Test
    @Order(5)
    void 게시글_삭제() {
        Long qnaId = 1L;
        qnaService.deleteQna(qnaId);
    }

}
