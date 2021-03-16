package com.mhkim.tms.board.controller;

import static com.mhkim.tms.common.ApiResult.ok;
import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhkim.tms.board.controller.dto.BoardAddDto;
import com.mhkim.tms.board.controller.dto.BoardDto;
import com.mhkim.tms.board.controller.dto.BoardEditDto;
import com.mhkim.tms.board.service.BoardService;
import com.mhkim.tms.common.ApiResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = { "1. Board" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회한다.")
    @GetMapping(value = "/list")
    public ApiResult<List<BoardDto>> getBoardList() {
        return ok(boardService.getBoardList()
                .stream()
                .map(BoardDto::new)
                .collect(toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회한다.")
    @GetMapping(value = "/{seq}")
    public ApiResult<BoardDto> getBoard(@PathVariable("boardId") Long boardId) throws Exception {
        return ok(boardService.getBoard(boardId)
                .map(BoardDto::new)
                .orElseThrow(Exception::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 추가", notes = "게시글을 추가한다")
    @PostMapping(value = "/add")
    public ApiResult<BoardDto> addBoard(@RequestBody @Valid BoardAddDto param) throws Exception {
        return ok(boardService.addBoard(param.toEntity())
                .map(BoardDto::new)
                .orElseThrow(Exception::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @PutMapping(value = "/{seq}")
    public ApiResult<BoardDto> edit(@PathVariable("boardId") Long boardId, @RequestBody @Valid BoardEditDto param)
            throws Exception {
        return ok(boardService.editBoard(boardId, param.toEntity())
                .map(BoardDto::new)
                .orElseThrow(Exception::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping(value = "/{seq}")
    public ApiResult<BoardDto> delete(@PathVariable("boardId") Long boardId) throws Exception {
        return ok(boardService.deleteBoard(boardId)
                .map(BoardDto::new)
                .orElseThrow(Exception::new), HttpStatus.OK);
    }

}
