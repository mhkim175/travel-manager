package com.mhkim.tms.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mhkim.tms.v1.board.entity.Qna;
import com.mhkim.tms.v1.board.service.QnaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        qna = new Qna(qnaId, userName, title, content, createdAt, modifiedAt);
    }

    @Test
    @Order(1)
    void 게시글_등록() {
        Qna qnaResult = qnaService.addQna(qna.getUserName(), qna.getTitle(), qna.getContent())
                .orElse(null);
        assertThat(qnaResult).isNotNull();
        assertThat(qnaResult.getUserName()).isEqualTo(qna.getUserName());
        assertThat(qnaResult.getTitle()).isEqualTo(qna.getTitle());
        assertThat(qnaResult.getContent()).isEqualTo(qna.getContent());
        log.info("QnA: {}", qnaResult);
    }

    @Test
    @Order(2)
    void 게시글_전체_조회() {
        List<Qna> qnas = qnaService.getQnaList();
        assertThat(qnas).isNotNull().hasSize(1);
        log.info("Found All: {}", qnas);
    }

    @Test
    @Order(3)
    void 게시글_단건_조회() {
        Qna qnaResult = qnaService.getQna(1L).orElse(null);
        assertThat(qnaResult).isNotNull();
        log.info("Found by {}: {}", 1L, qnaResult);
    }

    @Test
    @Order(4)
    void 게시글_수정() {
        String title = "updatedtitle";
        String content = "updatedcontent";
        Qna qnaResult = qnaService.updateQna(1L, title, content).orElse(null);
        assertThat(qnaResult).isNotNull();
        log.info("Updated QnA: {}", qnaResult);
    }

    @Test
    @Order(5)
    void 게시글_삭제() {
        Long qnaId = 1L;
        qnaService.deleteQna(qnaId);
        log.info("Deleted QnA: qnaId is {}", qnaId);
    }

}
