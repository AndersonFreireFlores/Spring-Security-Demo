package andersonfflores.springsecuritydemo.models.DTOs;

import andersonfflores.springsecuritydemo.models.Enums.UserRole;

public record UserDTO(String name, String email, String role) {
}
