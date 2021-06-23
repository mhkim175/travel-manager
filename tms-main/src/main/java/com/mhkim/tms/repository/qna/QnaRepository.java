package com.mhkim.tms.repository.qna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.qna.Qna;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
}
