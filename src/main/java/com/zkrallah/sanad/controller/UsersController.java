package com.zkrallah.sanad.controller;

import com.zkrallah.sanad.dtos.UpdateUserDto;
import com.zkrallah.sanad.entity.User;
import com.zkrallah.sanad.response.ApiResponse;
import com.zkrallah.sanad.response.MessageResponse;
import com.zkrallah.sanad.service.storage.StorageService;
import com.zkrallah.sanad.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.zkrallah.sanad.response.ApiResponse.createFailureResponse;
import static com.zkrallah.sanad.response.ApiResponse.createSuccessResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UserService userService;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        return ResponseEntity.ok(createSuccessResponse(userService.getUsers()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(createSuccessResponse(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(createFailureResponse("Could not fetch user: " + e.getMessage()));
        }
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserDto updateUser
    ) {
        try {
            User updatedUser = userService.updateUser(userId, updateUser);
            return ResponseEntity.ok(createSuccessResponse(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(createFailureResponse("Could not update user: " + e.getMessage()));
        }
    }

    @PostMapping("/{userId}/upload-image")
    public ResponseEntity<ApiResponse<MessageResponse>> upload(
            @RequestParam("file") MultipartFile multipartFile,
            @PathVariable Long userId
    ) {
        try {
            log.info("Receiving request on {} for userId {}", Thread.currentThread().getName(), userId.toString());
            String url = storageService.upload(multipartFile, userId).get();
            log.info("Responding on {} for userId {}", Thread.currentThread().getName(), userId);

            return ResponseEntity.ok(createSuccessResponse(new MessageResponse(url)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(createFailureResponse("Could not upload user's image: " + e.getMessage()));
        }
    }
}
