package christou.aristotelis.medirec.controllers;

import christou.aristotelis.medirec.entities.Patient;
import christou.aristotelis.medirec.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

  private final PatientRepository patientRepository;

  public PatientController(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  @GetMapping("/greet")
  public ResponseEntity<String> greet() {
    return new ResponseEntity<>("Hello", HttpStatus.OK);
  }

  @GetMapping("/")
  public Iterable<Patient> getUsers() {
    return patientRepository.findAll();
  }
}
