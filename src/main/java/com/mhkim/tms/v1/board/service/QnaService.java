package com.mhkim.tms.v1.board.service;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.v1.board.entity.Qna;
import com.mhkim.tms.v1.board.repository.QnaRepository;
import com.mhkim.tms.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QnaService {

    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    public List<Qna> getQnaList() {
        return qnaRepository.findAll();
    }

    public Optional<Qna> getQna(Long qnaId) {
        return qnaRepository.findById(qnaId);
    }

    @Transactional
    public Qna addQna(String title, String content, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    Qna qna = Qna.builder()
                            .title(title)
                            .content(content)
                            .user(user)
                            .build();
                    return qnaRepository.save(qna);
                }).orElseThrow(() -> new CDataNotFoundException("User not found"));
    }

    @Transactional
    public Qna updateQna(Long qnaId, String title, String content) {
        return getQna(qnaId)
                .map(qna -> {
                    qna.updateQna(title, content);
                    return qnaRepository.save(qna);
                }).orElseThrow(() -> new CDataNotFoundException("Board not found"));
    }

    @Transactional
    public Qna deleteQna(Long qnaId) {
        return getQna(qnaId)
                .map(qna -> {
                    qnaRepository.deleteById(qna.getQnaId());
                    return qna;
                }).orElseThrow(() -> new CDataNotFoundException("Board not found"));
    }

}
