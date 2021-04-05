package com.mhkim.tms.v1.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mhkim.tms.auth.oauth.SocialUser;
import com.mhkim.tms.v1.user.dto.SessionUser;

import lombok.RequiredArgsConstructor;

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
        
        if(user != null){
            mav.addObject("user", user);
        }
        
        mav.setViewName("user/test/home");
        return mav;
    }

    /**
     * 로그인 페이지
     */
    @GetMapping(value = "/user/test/login")
    public ModelAndView singin(ModelAndView mav) {
        mav.setViewName("user/test/login");
        return mav;
    }

    /**
     * 게시판 페이지
     */
    @GetMapping(value = "/user/test/board")
    public ModelAndView board(ModelAndView mav) {
        mav.setViewName("user/test/board");
        return mav;
    }

}
