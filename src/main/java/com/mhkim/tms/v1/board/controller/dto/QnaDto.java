package com.mhkim.tms.v1.board.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mhkim.tms.v1.board.entity.Qna;
import com.mhkim.tms.v1.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class QnaDto {

    @Getter
    @Setter
    public static class Response {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaId;

        @ApiModelProperty(value = "제목")
        private String title;

        @ApiModelProperty(value = "내용")
        private String content;

        @ApiModelProperty(value = "작성자 정보")
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private User user;

        public Response(Qna source) {
            BeanUtils.copyProperties(source, this);
        }

    }

    @Getter
    @Setter
    public static class Add {

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

        @ApiModelProperty(value = "작성자 아이디", required = true)
        private Long userId;

    }

    @Getter
    @Setter
    public static class Mod {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaId;

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

    }

    @Getter
    @Setter
    public static class Del {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaId;

    }

}
