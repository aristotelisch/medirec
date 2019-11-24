package christou.aristotelis.medirec.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "visits")
public class Visit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  private String symptoms;
  private String clinicalExamination;
  private String note;

  @ColumnDefault("0.00")
  private Double cost;

  @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LabFile> labFiles = new ArrayList<>();

  private Instant reexaminationAt;

  @CreationTimestamp private Instant createdAt;
}
