package christou.aristotelis.medirec.repositories;

import christou.aristotelis.medirec.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);

  Optional<User> findByEmail(String email);

  List<User> findByIdIn(List<Long> userIds);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
