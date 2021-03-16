package com.mhkim.tms.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mhkim.tms.board.domain.Board;
import com.mhkim.tms.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoardList() {
        return Lists.newArrayList(boardRepository.findAll());
    }
    
    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    public Optional<Board> addBoard(Board param) {
        return Optional.of(boardRepository.save(param));
    }
    
    @Transactional
    public Optional<Board> editBoard(Long boardId, Board param) {
        return getBoard(boardId).map(board -> {
            board.setServerUpdate(param.getTitle(), param.getContent());
            return boardRepository.save(board);
        });
    }

    @Transactional
    public Optional<Board> deleteBoard(Long boardId) {
        return getBoard(boardId).map(board -> {
            boardRepository.deleteById(board.getBoardId());
            return board;
        });
    }

}
