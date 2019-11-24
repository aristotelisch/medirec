package christou.aristotelis.medirec.repositories;

import christou.aristotelis.medirec.entities.LabFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabFileRepository extends CrudRepository<LabFile, String> {
}
