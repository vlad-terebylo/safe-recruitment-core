package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.dto.BooleanResponseDto;
import com.tvo.technologies.saferecruitment.model.dto.ChangePsswdRequestDto;
import com.tvo.technologies.saferecruitment.model.dto.UserUpdateDto;
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

    @PostMapping
    public ResponseEntity<BooleanResponseDto> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(new BooleanResponseDto(this.userService.addNewUser(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BooleanResponseDto> updateUser(
            @PathVariable String id,
            @RequestBody UserUpdateDto userUpdateDto) {

        return ResponseEntity.ok(new BooleanResponseDto(this.userService.updateUser(id, new User(
                userUpdateDto.name(),
                userUpdateDto.surname(),
                userUpdateDto.experience(),
                userUpdateDto.educationLevel(),
                userUpdateDto.targetPosition(),
                userUpdateDto.additionalInformation()
        ))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BooleanResponseDto> changePsswd(
            @PathVariable String id,
            @RequestBody ChangePsswdRequestDto changePsswdRequestDto) {
        return ResponseEntity.ok(new BooleanResponseDto(this.userService.changePsswd(id, changePsswdRequestDto.psswd())));
    }
}
