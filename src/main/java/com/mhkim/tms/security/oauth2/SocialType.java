package com.mhkim.tms.security.oauth2;

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

    public static SocialType of(String name) {
        for (SocialType state : SocialType.values()) {
            if (state.name().equalsIgnoreCase(name)) {
                return state;
            }
        }
        return NONE;
    }
    
}
