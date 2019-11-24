package christou.aristotelis.medirec;

import christou.aristotelis.medirec.entities.Visit;
import christou.aristotelis.medirec.repositories.PatientRepository;
import christou.aristotelis.medirec.repositories.UserRepository;
import christou.aristotelis.medirec.repositories.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class InitializeApp implements CommandLineRunner {
  @Autowired private VisitRepository visitRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private PatientRepository patientRepository;

  private static final Logger logger = LoggerFactory.getLogger(InitializeApp.class);

  @Override
  public void run(String... args) throws Exception {
    logger.info("Hello from app Initializer !");
    logger.warn("this is a warn message");
    logger.error("this is a error message");

//    logger.info(userRepository.findById(1L).get().toString());
////    logger.info(patientRepository.findAll().toString());
//    logger.info(visitRepository.findById(1L).get().toString());

//    // Create a new visit
//    Visit visit = new Visit();
//    visit.setClinicalExamination("Test examination results");
//    visit.setCost(50.00);
//    visit.setNote("Test Note");
//    visit.setPatient(patientRepository.findById(1L).get());
//    visitRepository.save(visit);

  }
}
