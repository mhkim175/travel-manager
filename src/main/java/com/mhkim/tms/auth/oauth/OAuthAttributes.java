package com.mhkim.tms.auth.oauth;

import java.util.Map;

import com.mhkim.tms.v1.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String email;
    private String name;
    private String registrationId;
    private String nameAttributeKey;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String registrationId, String nameAttributeKey, String email, String name) {
        this.attributes = attributes;
        this.email = email;
        this.name = name;
        this.registrationId = registrationId;
        this.nameAttributeKey = nameAttributeKey;
       
    }

    public static OAuthAttributes of(Map<String, Object> attributes, String registrationId, String userNameAttributeName) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .socialType(SocialType.of(registrationId))
                .role(Role.GUSET)
                .build();
    }

}
