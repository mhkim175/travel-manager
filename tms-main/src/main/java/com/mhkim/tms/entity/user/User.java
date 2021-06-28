package com.mhkim.tms.entity.user;

import com.mhkim.tms.entity.BaseTimeEntity;
import com.mhkim.tms.security.oauth2.Role;
import com.mhkim.tms.security.oauth2.SocialType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.mhkim.tms.util.ValidationUtils.checkEmail;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

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

    @Builder
    public User(Long userIdx, String email, String name, SocialType socialType, Role role) {
        checkArgument(isNotEmpty(email), "email must be provided.");
        checkArgument(email.length() >= 4 && email.length() <= 50, "email length must be between 4 and 50 characters.");
        checkArgument(checkEmail(email), "Invalid email address: " + email);

        this.userIdx = userIdx;
        this.email = email;
        this.name = name;
        this.socialType = socialType;
        this.role = role;
    }

    public void updateUser(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
