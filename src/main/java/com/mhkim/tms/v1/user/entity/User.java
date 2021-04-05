package com.mhkim.tms.v1.user.entity;

import static com.google.common.base.Preconditions.checkArgument;
import static com.mhkim.tms.util.ValidationUtils.checkEmail;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mhkim.tms.auth.oauth.Role;
import com.mhkim.tms.auth.oauth.SocialType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;;

    @Builder
    public User(Long userId, String email, String name, SocialType socialType, Role role, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        checkArgument(isNotEmpty(email), "email must be provided.");
        checkArgument(email.length() >= 4 && email.length() <= 50, "email length must be between 4 and 50 characters.");
        checkArgument(checkEmail(email), "Invalid email address: " + email);

        this.userId = userId;
        this.email = email;
        this.name = name;
        this.socialType = socialType;
        this.role = role;
        this.createdAt = defaultIfNull(createdAt, now());
        this.modifiedAt = defaultIfNull(modifiedAt, now());
    }

    public void updateUser(String name) {
        this.name = name;
    }
    
    public String getRoleKey() {
        return this.role.getKey();
    }

}
