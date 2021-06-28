package com.mhkim.tms.controller.v1.qna;

import com.mhkim.tms.controller.v1.qna.dto.QnaDto;
import com.mhkim.tms.service.qna.QnaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"QnA"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/qnas")
public class QnaController {

    private static final String ALL_QNAS = "all-qnas";

    private static final String GET_QNA = "get-qna";

    private static final String UPDATE_QNA = "update-qna";

    private static final String DELETE_QNA = "delete-qna";

    private final QnaService qnaService;

    @ApiOperation(value = "QnA 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<QnaDto.Response>> getQnas() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        qnaService.getQnas().stream()
                                .map(qna -> new QnaDto.Response(qna)
                                        .add(linkTo(methodOn(QnaController.class).getQna(qna.getQnaIdx())).withSelfRel())
                                        .add(linkTo(QnaController.class).slash(qna.getQnaIdx()).withRel(UPDATE_QNA))
                                        .add(linkTo(methodOn(QnaController.class).deleteQna(qna.getQnaIdx())).withRel(DELETE_QNA))
                                )
                                .collect(toList())
                )
                .add(linkTo(methodOn(QnaController.class).getQnas()).withSelfRel())
        );
    }

    @ApiOperation(value = "QnA 개별 조회")
    @GetMapping(value = "/{qnaIdx}")
    public ResponseEntity<QnaDto.Response> getQna(@PathVariable("qnaIdx") Long qnaIdx) {
        var qna = qnaService.getQna(qnaIdx);
        return ResponseEntity.ok(
                new QnaDto.Response(qna)
                        .add(linkTo(methodOn(QnaController.class).getQna(qnaIdx)).withSelfRel())
                        .add(linkTo(QnaController.class).slash(qnaIdx).withRel(UPDATE_QNA))
                        .add(linkTo(methodOn(QnaController.class).deleteQna(qnaIdx)).withRel(DELETE_QNA))
        );
    }

    @ApiOperation(value = "QnA 추가")
    @PostMapping
    public ResponseEntity<QnaDto.Response> addQna(@RequestBody QnaDto.Request param) {
        var qna = qnaService.addQna(param.toEntity());
        return new ResponseEntity<>(
                new QnaDto.Response(qna),
                HttpStatus.CREATED
        );
    }

    @ApiOperation(value = "QnA 수정")
    @PatchMapping(value = "/{qnaIdx}")
    public ResponseEntity<QnaDto.Response> updateQna(@PathVariable Long qnaIdx, @RequestBody QnaDto.Update param) {
        var qna = qnaService.updateQna(qnaIdx, param.getTitle(), param.getContent());
        return ResponseEntity.ok(
                new QnaDto.Response(qna)
                        .add(linkTo(methodOn(QnaController.class).updateQna(qnaIdx, param)).withSelfRel())
                        .add(linkTo(methodOn(QnaController.class).getQnas()).withRel(ALL_QNAS))
                        .add(linkTo(methodOn(QnaController.class).getQna(qnaIdx)).withRel(GET_QNA))
                        .add(linkTo(methodOn(QnaController.class).deleteQna(qnaIdx)).withRel(DELETE_QNA))
        );
    }

    @ApiOperation(value = "QnA 삭제")
    @DeleteMapping(value = "/{qnaIdx}")
    public ResponseEntity<QnaDto.Response> deleteQna(@PathVariable Long qnaIdx) {
        var qna = qnaService.deleteQna(qnaIdx);
        return ResponseEntity.ok(
                new QnaDto.Response(qna)
                        .add(linkTo(methodOn(QnaController.class).getQnas()).withSelfRel())
        );
    }

}
