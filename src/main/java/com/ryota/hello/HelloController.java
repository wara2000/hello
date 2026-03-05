package com.ryota.hello;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.ryota.hello.service.UserService;
import com.ryota.hello.dto.UserCreateRequest;
import com.ryota.hello.dto.UserResponse;
import com.ryota.hello.dto.UserUpdateRequest;
import com.ryota.hello.api.ApiResponse;


@RestController
@RequestMapping("/users")

public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(
        @Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.success(userService.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UserUpdateRequest request) {
        return ApiResponse.success(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.success(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(@PathVariable Long id) {
        return ApiResponse.success(userService.findByIdResponse(id));
    }
}