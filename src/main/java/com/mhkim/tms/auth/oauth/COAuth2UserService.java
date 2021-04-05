package com.mhkim.tms.auth.oauth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mhkim.tms.v1.user.dto.SessionUser;
import com.mhkim.tms.v1.user.entity.User;
import com.mhkim.tms.v1.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Custom COAuth2UserService
 */
@RequiredArgsConstructor
@Service
public class COAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        
        OAuthAttributes attributes = OAuthAttributes.of(oAuth2User.getAttributes(), registrationId, userNameAttributeName);
        
        User user = updateUser(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User updateUser(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(u -> {
                    u.updateUser(attributes.getName());
                    return u;
                }).orElse(attributes.toEntity());

        return userRepository.save(user);
    }
    
}
