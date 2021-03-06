package christou.aristotelis.medirec.repositories;

import christou.aristotelis.medirec.entities.Visit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
    Iterable<Visit>  findVisitsByPatientId(Long id);
}
