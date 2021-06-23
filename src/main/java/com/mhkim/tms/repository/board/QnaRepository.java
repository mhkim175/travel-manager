package com.mhkim.tms.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.board.Qna;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
}
