package christou.aristotelis.medirec.controllers;

import christou.aristotelis.medirec.entities.Patient;
import christou.aristotelis.medirec.exception.ResourceNotFoundException;
import christou.aristotelis.medirec.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

  private final PatientRepository patientRepository;

  public PatientController(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  // Get All Patients
  @GetMapping("/patients")
  public Iterable<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  // Create a new Patient
  @PostMapping("/patients")
  public Patient createPatient(@Valid @RequestBody Patient patient) {
    return patientRepository.save(patient);
  }

  // Get a Single Patient
  @GetMapping("/patients/{id}")
  public Patient getPatientById(@PathVariable(value = "id") Long patientId) {
    return patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
  }

  // Update a Patient
  @PutMapping("/patients/{id}")
  public Patient updateNote(@PathVariable(value = "id") Long patientId, @Valid @RequestBody Patient patientDetails) {
    Patient patient  = patientRepository.findById(patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

    patient.setAmka(patientDetails.getAmka());
    patient.setBirthDate(patientDetails.getBirthDate());
    patient.setFirstName(patientDetails.getFirstName());
    patient.setLastName(patientDetails.getLastName());
    patient.setFatherName(patientDetails.getFatherName());
    patient.setGender(patientDetails.getGender());
    patient.setInsuranceType(patientDetails.getInsuranceType());
    patient.setNotes(patientDetails.getNotes());

    return patientRepository.save(patient);
  }

  // Delete a Patient
  @DeleteMapping("/patients/{id}")
  public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Long patientId) {
    Patient patient  = patientRepository.findById(patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
    patientRepository.delete(patient);

    return ResponseEntity.ok().build();
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
