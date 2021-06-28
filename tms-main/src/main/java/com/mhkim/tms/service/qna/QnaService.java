package com.mhkim.tms.service.qna;

import com.mhkim.tms.entity.qna.Qna;
import com.mhkim.tms.entity.user.User;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.qna.QnaRepository;
import com.mhkim.tms.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QnaService {

    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    public List<Qna> getQnas() {
        return qnaRepository.findAll();
    }

    public Qna getQna(Long qnaIdx) {
        return qnaRepository.findById(qnaIdx)
                .orElseThrow(() -> new NotFoundException(Qna.class, qnaIdx));
    }

    @Transactional
    public Qna addQna(Qna qnaRequest) {
        return userRepository.findById(qnaRequest.getUser().getUserIdx())
                .map(user -> {
                    Qna qna = Qna.builder()
                            .title(qnaRequest.getTitle())
                            .content(qnaRequest.getContent())
                            .user(user)
                            .build();
                    return save(qna);
                })
                .orElseThrow(() -> new NotFoundException(User.class, qnaRequest.getUser().getUserIdx()));
    }

    @Transactional
    public Qna updateQna(Long qnaIdx, String title, String content) {
        return qnaRepository.findById(qnaIdx)
                .map(qna -> {
                    qna.updateQna(title, content);
                    return save(qna);
                })
                .orElseThrow(() -> new NotFoundException(Qna.class, qnaIdx));
    }

    @Transactional
    public Qna deleteQna(Long qnaIdx) {
        return qnaRepository.findById(qnaIdx)
                .map(qna -> {
                    qnaRepository.deleteById(qna.getQnaIdx());
                    return qna;
                })
                .orElseThrow(() -> new NotFoundException(Qna.class, qnaIdx));
    }

    private Qna save(Qna qna) {
        return qnaRepository.save(qna);
    }

}
