package com.mhkim.tms.v1.user.controller.dto;

import com.mhkim.tms.security.oauth2.Role;
import com.mhkim.tms.security.oauth2.SocialType;
import com.mhkim.tms.v1.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class UserDto {

    private Long userId;
    private String email;
    private String name;
    private SocialType socialType;
    private Role role;

    public UserDto(User source) {
        BeanUtils.copyProperties(source, this);
    }

}