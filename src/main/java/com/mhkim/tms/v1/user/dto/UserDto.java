package com.mhkim.tms.v1.user.dto;

import org.springframework.beans.BeanUtils;

import com.mhkim.tms.auth.oauth.Role;
import com.mhkim.tms.auth.oauth.SocialType;
import com.mhkim.tms.v1.user.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
