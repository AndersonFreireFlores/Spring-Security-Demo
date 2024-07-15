package andersonfflores.springsecuritydemo.Repositories;

import andersonfflores.springsecuritydemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    UserDetails findByName(String name);
}
