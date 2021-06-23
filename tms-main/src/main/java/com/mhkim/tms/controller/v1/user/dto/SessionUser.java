package com.mhkim.tms.controller.v1.user.dto;

import com.mhkim.tms.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

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
