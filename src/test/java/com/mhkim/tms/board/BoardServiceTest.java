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

import com.mhkim.tms.board.domain.Board;
import com.mhkim.tms.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    private Board board;

    @BeforeAll
    void setUp() {
        Long boardId = null;
        String userName = "mhkim";
        String title = "title";
        String content = "content";
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        board = new Board(boardId, userName, title, content, createdAt, modifiedAt);
    }

    @Test
    @Order(1)
    void 게시글_등록() {
        Board boardResult = boardService.addBoard(board.getUserName(), board.getTitle(), board.getContent())
                .orElse(null);
        assertThat(boardResult).isNotNull();
        assertThat(boardResult.getUserName()).isEqualTo(board.getUserName());
        assertThat(boardResult.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardResult.getContent()).isEqualTo(board.getContent());
        log.info("Board: {}", boardResult);
    }

    @Test
    @Order(2)
    void 게시글_전체_조회() {
        List<Board> boards = boardService.getBoardList();
        assertThat(boards).isNotNull().hasSize(1);
        log.info("Found All: {}", boards);
    }

    @Test
    @Order(3)
    void 게시글_단건_조회() {
        Board boardResult = boardService.getBoard(1L).orElse(null);
        assertThat(boardResult).isNotNull();
        log.info("Found by {}: {}", 1L, boardResult);
    }

    @Test
    @Order(4)
    void 게시글_수정() {
        String title = "updatedtitle";
        String content = "updatedcontent";
        Board boardResult = boardService.updateBoard(1L, title, content).orElse(null);
        assertThat(boardResult).isNotNull();
        log.info("Updated Board: {}", boardResult);
    }

    @Test
    @Order(5)
    void 게시글_삭제() {
        Long boardId = 1L;
        boardService.deleteBoard(boardId);
        log.info("Deleted Board: BoardId is {}", boardId);
    }

}
