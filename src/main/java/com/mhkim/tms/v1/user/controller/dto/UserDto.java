package com.mhkim.tms.v1.user.controller.dto;

import com.mhkim.tms.security.oauth2.Role;
import com.mhkim.tms.security.oauth2.SocialType;
import com.mhkim.tms.v1.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class Response {

        private Long userId;
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

        private Long userId;
        private String email;
        private String name;

    }

}
