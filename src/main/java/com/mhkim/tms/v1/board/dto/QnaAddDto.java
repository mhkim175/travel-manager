package com.mhkim.tms.v1.board.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaAddDto {

    @ApiModelProperty(value = "글쓴이", required = true)
    private String userName;

    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    private String content;

}
