package christou.aristotelis.medirec.repositories;

import christou.aristotelis.medirec.entities.Role;
import christou.aristotelis.medirec.entities.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
