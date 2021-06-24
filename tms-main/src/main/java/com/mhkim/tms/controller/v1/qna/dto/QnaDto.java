package com.mhkim.tms.controller.v1.qna.dto;

import com.mhkim.tms.entity.qna.Qna;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class QnaDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "qna", collectionRelation = "qnas")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "QnA ID")
        private Long qnaIdx;

        @ApiModelProperty(value = "제목")
        private String title;

        @ApiModelProperty(value = "내용")
        private String content;

        @ApiModelProperty(value = "작성자 ID")
        private Long userIdx;

        @ApiModelProperty(value = "작성자 이메일")
        private String email;

        @ApiModelProperty(value = "작성자명")
        private String name;

        public Response(Qna qna) {
            this.qnaIdx = qna.getQnaIdx();
            this.title = qna.getTitle();
            this.content = qna.getContent();
            this.userIdx = qna.getUser().getUserIdx();
            this.email = qna.getUser().getEmail();
            this.name = qna.getUser().getName();
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

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

    }

}
