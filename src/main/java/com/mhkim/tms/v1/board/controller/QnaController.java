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
import com.mhkim.tms.v1.board.dto.QnaAddDto;
import com.mhkim.tms.v1.board.dto.QnaDto;
import com.mhkim.tms.v1.board.dto.QnaUpdateDto;
import com.mhkim.tms.v1.board.service.QnaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = { "1. QnA" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class QnaController {

    private final QnaService anaService;

    @ApiOperation(value = "게시글 목록 조회")
    @GetMapping(value = "/qnas")
    public ApiResult<List<QnaDto>> getQnaList() {
        return ok(anaService.getQnaList().stream()
                .map(QnaDto::new)
                .collect(toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping(value = "/qna/{qnaId}")
    public ApiResult<QnaDto> getQna(@PathVariable("qnaId") Long qnaId) {
        return ok(anaService.getQna(qnaId)
                .map(QnaDto::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }
    
    @ApiOperation(value = "게시글 추가")
    @PostMapping(value = "/qna/add")
    public ApiResult<QnaDto> addQna(@RequestBody QnaAddDto param) {
        return ok(anaService.addQna(param.getUserName(), param.getTitle(), param.getContent())
                .map(QnaDto::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping(value = "/qna/{qnaId}")
    public ApiResult<QnaDto> updateQna(@PathVariable("qnaId") Long qnaId, @RequestBody QnaUpdateDto param) {
        return ok(anaService.updateQna(qnaId, param.getTitle(), param.getContent())
                .map(QnaDto::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping(value = "/qna/{qnaId}")
    public ApiResult<QnaDto> deleteQna(@PathVariable("qnaId") Long qnaId) {
        anaService.deleteQna(qnaId);
        return ok(HttpStatus.OK);
    }

}
