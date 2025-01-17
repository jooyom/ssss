package com.ormi.storywave.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/mypage")
public class AdminController {

    @Autowired
    private UserService userService;

    private List<User> users = new ArrayList<>();

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "mypage/userList2";
    }

 /*   @PutMapping("/updateUserStatus/{userId}")
    public ResponseEntity<Map<String, String>> updateUserStatus(
            @PathVariable("userId") String userId,
            @RequestBody User updateUserStatus) {

        User user = users.stream()
                .filter(m -> m.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지못하였습니다."));

        user.setActiveStatus(updateUserStatus.isActiveStatus());

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mypage/{userId}")
    @ResponseBody
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String userId) {
        User user = userService.findById(userId);
        if (user != null) {
            UserDto userDto = UserDto.fromUsers(user);
            return ResponseEntity.ok(userDto);
        } else {
            // 적절한 오류 처리
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/mypage/{userId}")
    @ResponseBody
    public UserDto getUserInfo(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return UserDto.fromUsers(user);
        } else {
            // 적절한 오류 처리 또는 기본값 반환
            return new UserDto();
        }

    }
}
