package andersonfflores.springsecuritydemo.Controllers;

import andersonfflores.springsecuritydemo.Repositories.UserRepository;
import andersonfflores.springsecuritydemo.models.DTOs.AuthenticationDTO;
import andersonfflores.springsecuritydemo.models.DTOs.RegisterDTO;
import andersonfflores.springsecuritydemo.models.Enums.UserRole;
import andersonfflores.springsecuritydemo.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated RegisterDTO data ){
    var namePassword = new UsernamePasswordAuthenticationToken(data.name(), data.password());
    var auth = authenticationManager.authenticate(namePassword);
    return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if (this.userRepository.findByName(data.name()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),encriptedPassword, UserRole.valueOf(data.role()));
        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
