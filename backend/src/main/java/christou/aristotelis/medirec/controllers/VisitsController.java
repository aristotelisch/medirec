package christou.aristotelis.medirec.controllers;

import christou.aristotelis.medirec.entities.Patient;
import christou.aristotelis.medirec.entities.Visit;
import christou.aristotelis.medirec.exception.ResourceNotFoundException;
import christou.aristotelis.medirec.repositories.PatientRepository;
import christou.aristotelis.medirec.repositories.VisitRepository;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VisitsController {
  @Autowired VisitRepository visitRepository;
  @Autowired PatientRepository patientRepository;

  // Get all visits of a patient
  @GetMapping("/patients/{id}/visits")
  public Iterable<Visit> getAllVisitsByPatient(@PathVariable("id") Long id) {
    return visitRepository.findVisitsByPatientId(id);
  }

  // Get a specific visit
  @GetMapping("/visits/{id}")
  public Visit getVisit(@PathVariable("id") Long id) {
    return visitRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Visit", "id", id));
  }

  // Create visit
  @PostMapping("/patients/{patientId}/visits")
  public Visit createVisit(@PathVariable("patientId") Long patientId, @RequestBody Visit visit) {
    Patient patient =
        patientRepository
            .findById(patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient", "patientId", patientId));

    visit.setPatient(patient);
    return visitRepository.save(visit);
  }

  // Update visit
  @PutMapping("/visits/{id}")
  public Visit updateVisit(@PathVariable("id") Long id, @RequestBody Visit visitDetails) {
    Visit visit =
        visitRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Visit", "Id", id));

    visit.setNote(visitDetails.getNote());
    visit.setPatient(visitDetails.getPatient());
    visit.setCost(visitDetails.getCost());
    visit.setClinicalExamination(visitDetails.getClinicalExamination());
    visit.setReexaminationAt(visitDetails.getReexaminationAt());
    visit.setSymptoms(visitDetails.getSymptoms());

    return visitRepository.save(visit);
  }

  // Delete visit
  @DeleteMapping("/visits/{id}")
  public ResponseEntity<?> deleteVisit(@PathVariable("id") Long id) {
    Visit visit =
            visitRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Visit", "Id", id));
    visitRepository.delete(visit);

    return ResponseEntity.ok().build();
  }
}
