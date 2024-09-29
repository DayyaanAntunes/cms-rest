package com.challenge.cms.user.presentation;

import com.challenge.cms.user.domain.command.UserCommand;
import com.challenge.cms.user.domain.mapper.UserMapper;
import com.challenge.cms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserJson> createUser(@Valid @RequestBody UserCommand userCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserJson(userService.save(userCommand)));
    }

    @GetMapping
    public ResponseEntity<List<UserJson>> fetchAllUsers() {
        return ResponseEntity.ok(userMapper.toListJson(userService.findAll()));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserJson> fetchUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toUserJson(userService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserJson> updateUser(@PathVariable Long id, @Valid @RequestBody UserCommand userCommand) {
        return ResponseEntity.ok(userMapper.toUserJson(userService.update(id,userCommand)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deleted-users")
    public ResponseEntity<List<UserJson>> fetchDeletedUsers() {
        return ResponseEntity.ok(userMapper.toListJson(userService.findAllDeletedUsers()));
    }

    @PatchMapping("/restore-user/{id}")
    public ResponseEntity<UserJson> restoreUser(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toUserJson(userService.restoreUser(id)));
    }

}
