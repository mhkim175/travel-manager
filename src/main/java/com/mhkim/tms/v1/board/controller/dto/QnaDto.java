package com.mhkim.tms.v1.board.controller.dto;

import com.mhkim.tms.v1.board.entity.Qna;
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

        @ApiModelProperty(value = "글쓴이")
        private String userName;

        @ApiModelProperty(value = "제목")
        private String title;

        @ApiModelProperty(value = "내용")
        private String content;

        public Response(Qna source) {
            BeanUtils.copyProperties(source, this);
        }

    }

    @Getter
    @Setter
    public static class Add {

        @ApiModelProperty(value = "글쓴이")
        private String userName;

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

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
