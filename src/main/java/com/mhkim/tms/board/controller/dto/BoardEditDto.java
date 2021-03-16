package com.mhkim.tms.board.controller.dto;

import javax.validation.constraints.NotEmpty;

import com.mhkim.tms.board.domain.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardEditDto {

    @ApiModelProperty(value = "제목")
    @NotEmpty
    String title;

    @ApiModelProperty(value = "내용")
    @NotEmpty
    String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }

}