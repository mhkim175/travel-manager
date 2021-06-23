package com.mhkim.tms.controller.v1.board;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.controller.v1.board.dto.QnaDto;
import com.mhkim.tms.entity.board.Qna;
import com.mhkim.tms.service.board.QnaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Api(tags = {"QnA"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class QnaController {

    private final QnaService qnaService;

    @ApiOperation(value = "QnA 목록 조회")
    @GetMapping(value = "/qnas")
    public ResponseEntity<List<QnaDto.Response>> getQnaList() {
        return ResponseEntity.ok(
                qnaService.getQnaList().stream()
                        .map(QnaDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "QnA 조회")
    @GetMapping(value = "/qna/{qnaIdx}")
    public ResponseEntity<QnaDto.Response> getQna(@PathVariable("qnaIdx") Long qnaIdx) {
        return ResponseEntity.ok(
                qnaService.getQna(qnaIdx)
                        .map(QnaDto.Response::new)
                        .orElseThrow(() -> new CDataNotFoundException("Board not found"))
        );
    }

    @ApiOperation(value = "QnA 추가")
    @PostMapping(value = "/qna/add")
    public ResponseEntity<QnaDto.Response> addQna(@RequestBody QnaDto.Add param) {
        Qna qna = qnaService.addQna(param.getTitle(), param.getContent(), param.getUserIdx());
        return new ResponseEntity<>(new QnaDto.Response(qna), HttpStatus.CREATED);
    }

    @ApiOperation(value = "QnA 수정")
    @PatchMapping(value = "/qna")
    public ResponseEntity<QnaDto.Response> updateQna(@RequestBody QnaDto.Mod param) {
        Qna qna = qnaService.updateQna(param.getQnaIdx(), param.getTitle(), param.getContent());
        return ResponseEntity.ok(new QnaDto.Response(qna));
    }

    @ApiOperation(value = "QnA 삭제")
    @DeleteMapping(value = "/qna")
    public ResponseEntity<QnaDto.Response> deleteQna(@RequestBody QnaDto.Del param) {
        Qna qna = qnaService.deleteQna(param.getQnaIdx());
        return ResponseEntity.ok(new QnaDto.Response(qna));
    }

}
