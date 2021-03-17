package com.mhkim.tms.board.controller.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateDto {

    @ApiModelProperty(value = "제목", required = true)
    @NotEmpty
    String title;

    @ApiModelProperty(value = "내용", required = true)
    @NotEmpty
    String content;

}