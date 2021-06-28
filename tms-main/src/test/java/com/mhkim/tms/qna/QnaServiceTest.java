package com.mhkim.tms.qna;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.mhkim.tms.entity.qna.Qna;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.qna.QnaRepository;
import com.mhkim.tms.service.qna.QnaService;
import com.mhkim.tms.entity.user.User;
import com.mhkim.tms.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class QnaServiceTest {

    @InjectMocks
    private QnaService qnaService;

    @Mock
    private QnaRepository qnaRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    private Qna expectedQna;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        MessageUtils.setMessageSourceAccessor(messageSourceAccessor);

        expectedUser = User.builder()
                .userIdx(1L)
                .email("user1111@test.com")
                .name("user1111")
                .build();

        expectedQna = Qna.builder()
                .qnaIdx(1L)
                .title("제목11")
                .content("내용내용1111")
                .user(expectedUser)
                .build();
    }

    @DisplayName(value = "QnA 전체 조회")
    @Test
    void getQnas() {
        // given
        given(qnaRepository.findAll()).willReturn(List.of(expectedQna));

        // when
        List<Qna> qnas = qnaService.getQnas();

        // then
        assertThat(qnas.size()).isEqualTo(1);
        assertThat(qnas.get(0).getQnaIdx()).isEqualTo(expectedQna.getQnaIdx());
        assertThat(qnas.get(0).getTitle()).isEqualTo(expectedQna.getTitle());
        assertThat(qnas.get(0).getContent()).isEqualTo(expectedQna.getContent());
        assertThat(qnas.get(0).getUser().getUserIdx()).isEqualTo(expectedQna.getUser().getUserIdx());

        log.info("qnas: {}", qnas);
    }

    @DisplayName(value = "QnA 개별 조회 성공")
    @Test
    void getQnaSuccess() {
        // given
        given(qnaRepository.findById(anyLong())).willReturn(Optional.of(expectedQna));

        // when
        Qna qna = qnaService.getQna(1L);

        // then
        assertThat(qna).isNotNull();
        assertThat(qna.getQnaIdx()).isEqualTo(expectedQna.getQnaIdx());
        assertThat(qna.getTitle()).isEqualTo(expectedQna.getTitle());
        assertThat(qna.getContent()).isEqualTo(expectedQna.getContent());
        assertThat(qna.getUser().getUserIdx()).isEqualTo(expectedQna.getUser().getUserIdx());

        log.info("qna: {}", qna);
    }

    @DisplayName(value = "QnA 개별 조회 실패")
    @Test
    void Fail() {
        // given
        given(qnaRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            qnaService.getQna(100L);
        });
    }

    @DisplayName(value = "QnA 추가 성공")
    @Test
    void addQnaSuccess() {
        // given
        given(qnaRepository.save(any(Qna.class))).willReturn(expectedQna);

        // when
        Qna qna = qnaService.addQna(expectedQna);

        // then
        assertThat(qna).isNotNull();
        assertThat(qna.getQnaIdx()).isEqualTo(expectedQna.getQnaIdx());
        assertThat(qna.getTitle()).isEqualTo(expectedQna.getTitle());
        assertThat(qna.getContent()).isEqualTo(expectedQna.getContent());
        assertThat(qna.getUser().getUserIdx()).isEqualTo(expectedQna.getUser().getUserIdx());

        log.info("qna: {}", qna);
    }

    @DisplayName(value = "QnA 수정 성공")
    @Test
    void updateQnaSuccess() {
        // given
        String title = "제목수정1111";
        String content = "내용수정1111";
        given(qnaRepository.findById(anyLong())).willReturn(Optional.of(expectedQna));

        // when
        Qna qna = qnaService.updateQna(1L, title, content);

        // then
        assertThat(qna).isNotNull();
        assertThat(qna.getQnaIdx()).isEqualTo(expectedQna.getQnaIdx());
        assertThat(qna.getTitle()).isEqualTo(expectedQna.getTitle());
        assertThat(qna.getContent()).isEqualTo(expectedQna.getContent());
        assertThat(qna.getUser().getUserIdx()).isEqualTo(expectedQna.getUser().getUserIdx());

        log.info("qna: {}", qna);
    }

    @DisplayName(value = "QnA 수정 실패")
    @Test
    void updateQnaFail() {
        // given
        String title = "제목수정1111";
        String content = "내용수정1111";
        given(qnaRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            qnaService.updateQna(100L, title, content);
        });
    }

    @DisplayName(value = "QnA 삭제 성공")
    @Test
    void deleteQnaSuccess() {
        // given
        given(qnaRepository.findById(anyLong())).willReturn(Optional.of(expectedQna));

        // when
        Qna qna = qnaService.deleteQna(1L);

        // then
        assertThat(qna).isNotNull();
        assertThat(qna.getQnaIdx()).isEqualTo(expectedQna.getQnaIdx());
        assertThat(qna.getTitle()).isEqualTo(expectedQna.getTitle());
        assertThat(qna.getContent()).isEqualTo(expectedQna.getContent());
        assertThat(qna.getUser().getUserIdx()).isEqualTo(expectedQna.getUser().getUserIdx());

        log.info("qna: {}", qna);
    }

    @DisplayName(value = "QnA 삭제 실패")
    @Test
    void deleteQnaFail() {
        // given
        given(qnaRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
           qnaService.deleteQna(100L);
        });
    }

}
