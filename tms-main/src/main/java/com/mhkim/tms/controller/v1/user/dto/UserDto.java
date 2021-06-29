package com.mhkim.tms.controller.v1.user.dto;

import com.mhkim.tms.entity.user.User;
import com.mhkim.tms.security.oauth2.Role;
import com.mhkim.tms.security.oauth2.SocialType;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class UserDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "user", collectionRelation = "users")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "사용자 ID")
        private Long userIdx;

        @ApiModelProperty(value = "사용자 이메일")
        private String email;

        @ApiModelProperty(value = "사용자명")
        private String name;

        @ApiModelProperty(value = "로그인 소셜 유형")
        private SocialType socialType;

        @ApiModelProperty(value = "사용자 역할")
        private Role role;

        public Response(User user) {
            this.userIdx = user.getUserIdx();
            this.email = user.getEmail();
            this.name = user.getName();
            this.socialType = user.getSocialType();
            this.role = user.getRole();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "사용자 이메일", required = true)
        private String email;

        @ApiModelProperty(value = "사용자명", required = true)
        private String name;

        @ApiModelProperty(value = "로그인 소셜 유형", required = true)
        private SocialType socialType;

        @ApiModelProperty(value = "사용자 역할", required = true)
        private Role role;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .name(name)
                    .socialType(socialType)
                    .role(role)
                    .build();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Update {

        @ApiModelProperty(value = "사용자명", required = true)
        private String name;

    }

    @Getter
    @Setter
    @ToString
    public static class Info {

        private Long userIdx;
        private String email;
        private String name;

    }

}
