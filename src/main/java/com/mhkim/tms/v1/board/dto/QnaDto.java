package com.mhkim.tms.v1.board.dto;

import org.springframework.beans.BeanUtils;

import com.mhkim.tms.v1.board.entity.Qna;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDto {

    @ApiModelProperty(value = "게시글 ID")
    private Long qnaId;

    @ApiModelProperty(value = "글쓴이")
    private String userName;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String content;

    public QnaDto(Qna source) {
        BeanUtils.copyProperties(source, this);
    }

}
