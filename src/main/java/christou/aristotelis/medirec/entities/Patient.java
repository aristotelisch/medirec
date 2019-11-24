package christou.aristotelis.medirec.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String fatherName;
    private String role;
    private String email;
    private String telephone;
    private String insuranceType;
    private String password;
    private String amka;

    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Visit> visits = new ArrayList<>();

    @CreationTimestamp
    private Instant createdAt;

}
