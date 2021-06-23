package com.mhkim.tms.controller.v1.user.dto;

import com.mhkim.tms.security.oauth2.Role;
import com.mhkim.tms.security.oauth2.SocialType;
import com.mhkim.tms.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class Response {

        private Long userIdx;
        private String email;
        private String name;
        private SocialType socialType;
        private Role role;

        public Response(User source) {
            BeanUtils.copyProperties(source, this);
        }

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
