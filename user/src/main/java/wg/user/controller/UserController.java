package wg.user.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wg.user.model.UserDetails;
import wg.user.model.UserResponse;
import wg.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final Environment env;

    public UserController(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    @GetMapping("/status")
    public String status() {
        return String.format("Working on port: %s with token: %s",
                env.getProperty("local.server.port"),
                env.getProperty("token.secret"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserDetails userDetails) {
        userService.createUser(userDetails);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
}
