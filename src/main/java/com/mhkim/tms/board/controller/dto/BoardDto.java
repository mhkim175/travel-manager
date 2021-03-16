package com.mhkim.tms.board.controller.dto;

import org.springframework.beans.BeanUtils;

import com.mhkim.tms.board.domain.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    @ApiModelProperty(value = "게시글 ID")
    Long boardId;

    @ApiModelProperty(value = "글쓴이")
    String userName;

    @ApiModelProperty(value = "제목")
    String title;

    @ApiModelProperty(value = "내용")
    String content;

    public BoardDto(Board source) {
        BeanUtils.copyProperties(source, this);
    }

}
