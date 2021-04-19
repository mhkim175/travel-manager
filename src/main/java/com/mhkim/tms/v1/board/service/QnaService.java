package com.mhkim.tms.v1.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhkim.tms.v1.board.entity.Qna;
import com.mhkim.tms.v1.board.repository.QnaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QnaService {

    private final QnaRepository qnaRepository;

    public List<Qna> getQnaList() {
        return qnaRepository.findAll();
    }
    
    public Optional<Qna> getQna(Long qnaId) {
        return qnaRepository.findById(qnaId);
    }

    @Transactional
    public Optional<Qna> addQna(String userName, String title, String content) {
        Qna qna = Qna.builder()
                .userName(userName)
                .title(title)
                .content(content).build();
        return Optional.of(qnaRepository.save(qna));
    }
    
    @Transactional
    public Optional<Qna> updateQna(Long qnaId, String title, String content) {
        return getQna(qnaId).map(qna -> {
            qna.updateQna(title, content);
            return qnaRepository.save(qna);
        });
    }

    @Transactional
    public void deleteQna(Long qnaId) {
        qnaRepository.deleteById(qnaId);
    }

}
