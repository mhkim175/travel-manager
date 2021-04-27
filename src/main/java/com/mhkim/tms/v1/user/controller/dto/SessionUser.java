package com.mhkim.tms.v1.user.controller.dto;

import com.mhkim.tms.v1.user.entity.User;
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
