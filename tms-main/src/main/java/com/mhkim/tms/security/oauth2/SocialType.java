package com.mhkim.tms.security.oauth2;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialType {

    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver"),
    NONE("none");

    private final String value;

    public static SocialType of(String value) {
        return Arrays.stream(SocialType.values())
                .filter(type -> type.value.equals(value))
                .findAny()
                .orElse(NONE);
    }
    
}
