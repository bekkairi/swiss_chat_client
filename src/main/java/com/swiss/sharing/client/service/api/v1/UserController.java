package com.swiss.sharing.client.service.api.v1;

import com.swiss.sharing.client.service.dto.UserDTO;
import com.swiss.sharing.client.service.entity.UserToken;
import com.swiss.sharing.client.service.services.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/users")
@Data
public class UserController {
    private final UserService userService;

    @PostMapping()
    @CrossOrigin(origins = "*")
    public UserDTO createUser(@Valid @RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public UserDTO connect(@RequestBody UserDTO user) {
        return userService.getUser(user);
    }

    @PostMapping("/validate")
    @CrossOrigin(origins = "*")
    public UserDTO connect(@RequestBody UserToken userToken) {
        return userService.validateUser(userToken.getEmail(), userToken.getToken());
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public Page<UserDTO> getUsers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        return userService.getUsers(pageable);
    }

}
