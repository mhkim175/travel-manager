package com.mhkim.tms.user;

import com.mhkim.tms.entity.user.User;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.user.UserRepository;
import com.mhkim.tms.service.user.UserService;
import com.mhkim.tms.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    private User expectedUser;

    @BeforeEach
    void setUp() {
        MessageUtils.setMessageSourceAccessor(messageSourceAccessor);

        expectedUser = User.builder()
                .userIdx(1L)
                .email("user001@test.com")
                .name("user001")
                .build();
    }

    @DisplayName(value = "사용자 전체 조회")
    @Test
    void getUsers() {
        // given
        given(userRepository.findAll()).willReturn(List.of(expectedUser));

        // when
        List<User> users = userService.getUsers();

        // then
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUserIdx()).isEqualTo(expectedUser.getUserIdx());
        assertThat(users.get(0).getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(users.get(0).getName()).isEqualTo(expectedUser.getName());
        assertThat(users.get(0).getSocialType()).isEqualTo(expectedUser.getSocialType());
        assertThat(users.get(0).getRole()).isEqualTo(expectedUser.getRole());

        log.info("users: {}", users);
    }

    @DisplayName(value = "사용자 개별 조회 성공")
    @Test
    void getUserSuccess() {
        // given
        given(userRepository.findById(anyLong())).willReturn(Optional.of(expectedUser));

        // when
        User user = userService.getUser(1L);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getUserIdx()).isEqualTo(expectedUser.getUserIdx());
        assertThat(user.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(user.getName()).isEqualTo(expectedUser.getName());
        assertThat(user.getSocialType()).isEqualTo(expectedUser.getSocialType());
        assertThat(user.getRole()).isEqualTo(expectedUser.getRole());

        log.info("user: {}", user);
    }

    @DisplayName(value = "사용자 개별 조회 실패")
    @Test
    void Fail() {
        // given
        given(userRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            userService.getUser(100L);
        });
    }

    @DisplayName(value = "사용자 추가 성공")
    @Test
    void addUserSuccess() {
        // given
        given(userRepository.save(any(User.class))).willReturn(expectedUser);

        // when
        User user = userService.addUser(expectedUser);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getUserIdx()).isEqualTo(expectedUser.getUserIdx());
        assertThat(user.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(user.getName()).isEqualTo(expectedUser.getName());
        assertThat(user.getSocialType()).isEqualTo(expectedUser.getSocialType());
        assertThat(user.getRole()).isEqualTo(expectedUser.getRole());

        log.info("user: {}", user);
    }

    @DisplayName(value = "사용자 수정 성공")
    @Test
    void updateUserSuccess() {
        // given
        String name = "user002";
        given(userRepository.findById(anyLong())).willReturn(Optional.of(expectedUser));

        // when
        User user = userService.updateUser(1L, name);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getUserIdx()).isEqualTo(expectedUser.getUserIdx());
        assertThat(user.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(user.getName()).isEqualTo(expectedUser.getName());
        assertThat(user.getSocialType()).isEqualTo(expectedUser.getSocialType());
        assertThat(user.getRole()).isEqualTo(expectedUser.getRole());

        log.info("user: {}", user);
    }

    @DisplayName(value = "사용자 수정 실패")
    @Test
    void updateUserFail() {
        // given
        String name = "user002";
        given(userRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            userService.updateUser(100L, name);
        });
    }

    @DisplayName(value = "사용자 삭제 성공")
    @Test
    void deleteUserSuccess() {
        // given
        given(userRepository.findById(anyLong())).willReturn(Optional.of(expectedUser));

        // when
        User user = userService.deleteUser(1L);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getUserIdx()).isEqualTo(expectedUser.getUserIdx());
        assertThat(user.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(user.getName()).isEqualTo(expectedUser.getName());
        assertThat(user.getSocialType()).isEqualTo(expectedUser.getSocialType());
        assertThat(user.getRole()).isEqualTo(expectedUser.getRole());

        log.info("user: {}", user);
    }

    @DisplayName(value = "사용자 삭제 실패")
    @Test
    void deleteUserFail() {
        // given
        given(userRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
           userService.deleteUser(100L);
        });
    }

}
