package christou.aristotelis.medirec.repositories;

import christou.aristotelis.medirec.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
