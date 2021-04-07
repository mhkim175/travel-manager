package com.mhkim.tms.v1.board.controller;

import static com.mhkim.tms.common.ApiResult.ok;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.common.ApiResult;
import com.mhkim.tms.v1.board.dto.BoardAddDto;
import com.mhkim.tms.v1.board.dto.BoardDto;
import com.mhkim.tms.v1.board.dto.BoardUpdateDto;
import com.mhkim.tms.v1.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = { "1. Board" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 목록 조회")
    @GetMapping(value = "/boards")
    public ApiResult<List<BoardDto>> getBoardList() {
        return ok(boardService.getBoardList().stream()
                .map(BoardDto::new)
                .collect(toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping(value = "/board/{boardId}")
    public ApiResult<BoardDto> getBoard(@PathVariable("boardId") Long boardId) {
        return ok(boardService.getBoard(boardId)
                .map(BoardDto::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }
    
    @ApiOperation(value = "게시글 추가")
    @PostMapping(value = "/board/add")
    public ApiResult<BoardDto> addBoard(@RequestBody BoardAddDto param) {
        return ok(boardService.addBoard(param.getUserName(), param.getTitle(), param.getContent())
                .map(BoardDto::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping(value = "/board/{boardId}")
    public ApiResult<BoardDto> updateBoard(@PathVariable("boardId") Long boardId, @RequestBody BoardUpdateDto param) {
        return ok(boardService.updateBoard(boardId, param.getTitle(), param.getContent())
                .map(BoardDto::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping(value = "/board/{boardId}")
    public ApiResult<BoardDto> deleteBoard(@PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);
        return ok(HttpStatus.OK);
    }

}
