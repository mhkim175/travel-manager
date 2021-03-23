package com.mhkim.tms.v1.board.controller.dto;

import org.springframework.beans.BeanUtils;

import com.mhkim.tms.v1.board.domain.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    @ApiModelProperty(value = "게시글 ID")
    private Long boardId;

    @ApiModelProperty(value = "글쓴이")
    private String userName;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String content;

    public BoardDto(Board source) {
        BeanUtils.copyProperties(source, this);
    }

}
