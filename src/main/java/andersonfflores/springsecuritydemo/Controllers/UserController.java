package andersonfflores.springsecuritydemo.Controllers;

import andersonfflores.springsecuritydemo.Services.UserService;
import andersonfflores.springsecuritydemo.models.DTOs.UserDTO;
import andersonfflores.springsecuritydemo.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public UserDTO createUser(User user) {
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(UUID id, User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(UUID id) {
        userService.deleteUser(id);
    }
}
