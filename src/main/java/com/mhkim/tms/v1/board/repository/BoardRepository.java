package com.mhkim.tms.v1.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.v1.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
