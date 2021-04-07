package com.mhkim.tms.security.oauth2;

import java.util.Map;

import com.mhkim.tms.v1.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String email;
    private String name;
    private String registrationId;
    private String nameAttributeKey;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String registrationId, String nameAttributeKey, String email,
            String name) {
        this.attributes = attributes;
        this.email = email;
        this.name = name;
        this.registrationId = registrationId;
        this.nameAttributeKey = nameAttributeKey;
    }

    public static OAuthAttributes of(Map<String, Object> attributes, String registrationId, String userNameAttributeName) {
        if (SocialType.KAKAO.equals(SocialType.of(registrationId))) {
            return ofKakao(attributes, registrationId, userNameAttributeName);
        } 
        if (SocialType.NAVER.equals(SocialType.of(registrationId))) {
            return ofNaver(attributes, registrationId);
        } 
        return ofDefault(attributes, registrationId, userNameAttributeName);
    }

    private static OAuthAttributes ofDefault(Map<String, Object> attributes, String registrationId, String userNameAttributeName) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(Map<String, Object> attributes, String registrationId, String userNameAttributeName) {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        return OAuthAttributes.builder()
                .attributes(attributes)
                .email((String) account.get("email"))
                .name((String) properties.get("nickname"))
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(Map<String, Object> attributes, String registrationId) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .attributes(response)
                .email((String) response.get("email"))
                .name((String) response.get("nickname"))
                .registrationId(registrationId)
                .nameAttributeKey("id")
                .build();
    }

    public User toUserEntity() {
        return User.builder().name(name).email(email).socialType(SocialType.of(registrationId)).role(Role.USER).build();
    }

}
