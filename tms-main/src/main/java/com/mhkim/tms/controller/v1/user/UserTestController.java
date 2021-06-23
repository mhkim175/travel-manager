package com.mhkim.tms.controller.v1.user;

import com.mhkim.tms.security.oauth2.SocialUser;
import com.mhkim.tms.controller.v1.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 소셜 로그인 테스트용 페이지 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserTestController {

    /**
     * 소셜 로그인 테스트 메인 페이지
     */
    @GetMapping(value = "/")
    public ModelAndView home(ModelAndView mav, @SocialUser SessionUser user) {

        if (user != null) {
            mav.addObject("user", user);
        }

        mav.setViewName("test/home");
        return mav;
    }

    /**
     * 게시판 테스트 페이지
     */
    @GetMapping(value = "/test/qna")
    public ModelAndView qna(ModelAndView mav) {
        mav.setViewName("test/qna");
        return mav;
    }

    /**
     * 여행정보 테스트 페이지
     */
    @GetMapping(value = "/test/travelinfo")
    public ModelAndView travelinfo(ModelAndView mav) {
        mav.setViewName("test/travelinfo");
        return mav;
    }

}
