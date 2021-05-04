package com.mhkim.tms.v1.board.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mhkim.tms.v1.board.entity.Qna;
import com.mhkim.tms.v1.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

public class QnaDto {

    @Getter
    @Setter
    @ToString
    public static class Response {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaIdx;

        @ApiModelProperty(value = "제목")
        private String title;

        @ApiModelProperty(value = "내용")
        private String content;

        @ApiModelProperty(value = "작성자 아이디")
        private Long userIdx;

        @ApiModelProperty(value = "작성자 이메일")
        private String email;

        @ApiModelProperty(value = "작성자명")
        private String name;

        public Response(Qna source) {
            BeanUtils.copyProperties(source, this);

            this.userIdx = source.getUser().getUserIdx();
            this.email = source.getUser().getEmail();
            this.name = source.getUser().getName();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Add {

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

        @ApiModelProperty(value = "작성자 아이디", required = true)
        private Long userIdx;

    }

    @Getter
    @Setter
    @ToString
    public static class Mod {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaIdx;

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

    }

    @Getter
    @Setter
    @ToString
    public static class Del {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaIdx;

    }

}
