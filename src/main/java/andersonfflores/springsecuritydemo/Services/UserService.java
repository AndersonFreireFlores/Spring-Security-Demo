package andersonfflores.springsecuritydemo.Services;

import andersonfflores.springsecuritydemo.Repositories.UserRepository;
import andersonfflores.springsecuritydemo.models.DTOs.UserDTO;
import andersonfflores.springsecuritydemo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getName(), user.getEmail(), user.getRole().toString()))
                .toList();
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO( user.getName(), user.getEmail(), user.getRole().toString()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO createUser(User user) {
        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail(), user.getRole().toString());
        userRepository.save(user);
        return userDTO;
    }

    public UserDTO updateUser(UUID id, User user) {
        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail(), user.getRole().toString());
        userRepository.save(user);
        return userDTO;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
