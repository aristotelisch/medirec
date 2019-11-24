package christou.aristotelis.medirec.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "lab_files")
public class LabFile {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  @JsonIgnore
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "visit_id")
  @JsonIgnore
  private Visit visit;

  private String fileName;
  private String fileType;

  @Lob private byte[] data;

  public LabFile(){super();}

  public LabFile(String fileName, String contentType, byte[] bytes) {
    this.fileName = fileName;
    this.fileType = contentType;
    this.data = bytes;
  }

  public LabFile(String fileName, String contentType, byte[] bytes, Patient patient, Visit visit) {
    this(fileName, contentType, bytes);
    this.patient = patient;
    this.visit = visit;
  }
}
