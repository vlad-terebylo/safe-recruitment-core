package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.dto.BooleanResponseDto;
import com.tvo.technologies.saferecruitment.model.dto.UserMetadataUpdateDto;
import com.tvo.technologies.saferecruitment.model.user.UserMetadata;
import com.tvo.technologies.saferecruitment.service.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserMetadataController {

    private final UserMetadataService userMetadataService;

    @GetMapping("/{id}")
    public ResponseEntity<UserMetadata> getUserMetadata(@PathVariable String id) {
        return ResponseEntity.ok(this.userMetadataService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BooleanResponseDto> updateUserMetadata(
            @PathVariable String id,
            @RequestBody UserMetadataUpdateDto userMetadataUpdateDto) {

        return ResponseEntity.ok(new BooleanResponseDto(this.userMetadataService.updateUser(id, new UserMetadata(
                userMetadataUpdateDto.name(),
                userMetadataUpdateDto.surname(),
                userMetadataUpdateDto.experience(),
                userMetadataUpdateDto.educationLevel(),
                userMetadataUpdateDto.targetPosition(),
                userMetadataUpdateDto.additionalInformation()
        ))));
    }
}
