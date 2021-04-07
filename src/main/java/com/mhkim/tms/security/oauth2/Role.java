package com.mhkim.tms.security.oauth2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "일반사용자"),
    GUSET("ROLE_GUEST", "게스트");

    private final String key;
    private final String title;

}
