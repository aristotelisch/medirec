package christou.aristotelis.medirec.repositories;

import christou.aristotelis.medirec.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
