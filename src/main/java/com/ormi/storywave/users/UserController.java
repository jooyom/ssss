package com.ormi.storywave.users;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String addUserForm(Model model) {
        return "join";
    }

    @PostMapping("/join")
    public String addUser(@ModelAttribute UserRequest.JoinDto joinDto) {
        UsersDto addedUser = userService.addUser(joinDto);
//        model.addAttribute("user", addedUser);
        return "welcome";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserRequest.LoginDto loginDto, Model model) {

        try { // userId를 받는 게 나을까 객체를 받는 게 나을까
            String userId = userService.loginUser(loginDto);
        } catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "login";
        }

        return "home"; // index_afterLogin에 맵핑이 안 된 게 많아서 오류 발생. 추후 변경

    }
}