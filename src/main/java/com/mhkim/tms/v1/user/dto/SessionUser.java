package com.mhkim.tms.v1.user.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.mhkim.tms.v1.user.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SessionUser implements Serializable {

    private String email;
    private String name;

    public SessionUser(User source) {
        BeanUtils.copyProperties(source, this);
    }

}
