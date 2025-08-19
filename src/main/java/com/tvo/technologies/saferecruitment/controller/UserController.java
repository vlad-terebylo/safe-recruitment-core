package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.dto.BooleanResponseDto;
import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BooleanResponseDto> updateUser(
            @PathVariable String id,
            @RequestBody User user) {
        return ResponseEntity.ok(new BooleanResponseDto(this.userService.updateUser(id, user)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BooleanResponseDto> changePassword(
            @PathVariable String id,
            @RequestBody User user) {
        return ResponseEntity.ok(new BooleanResponseDto(this.userService.changePassword(id, user)));
    }

    @PostMapping
    public ResponseEntity<BooleanResponseDto> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(new BooleanResponseDto(this.userService.addNewUser(user)));
    }
}
