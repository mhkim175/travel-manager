package com.mhkim.tms.board.controller.dto;

import javax.validation.constraints.NotEmpty;

import com.mhkim.tms.board.domain.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardAddDto {

    @ApiModelProperty(value = "글쓴이")
    @NotEmpty
    String userName;

    
    @ApiModelProperty(value = "제목")
    @NotEmpty
    String title;

    
    @ApiModelProperty(value = "내용")
    @NotEmpty
    String content;

    public Board toEntity() {
        return Board.builder()
                .userName(userName)
                .title(title)
                .content(content)
                .build();
    }

}
