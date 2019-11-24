package christou.aristotelis.medirec.controllers;

import christou.aristotelis.medirec.entities.LabFile;
import christou.aristotelis.medirec.entities.Patient;
import christou.aristotelis.medirec.entities.Visit;
import christou.aristotelis.medirec.exception.VisitNotFoundException;
import christou.aristotelis.medirec.payload.UploadFileResponse;
import christou.aristotelis.medirec.repositories.PatientRepository;
import christou.aristotelis.medirec.repositories.VisitRepository;
import christou.aristotelis.medirec.service.LabFileService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileController {

  private static final Logger logger = LoggerFactory.getLogger(FileController.class);

  @Autowired private LabFileService labFileService;
  @Autowired private PatientRepository patientRepository;
  @Autowired private VisitRepository visitRepository;

  @PostMapping("/uploadFile")
  public UploadFileResponse uploadFile(
      @RequestParam("file") MultipartFile file, @RequestParam("visit_id") Long visit_id) {
    Visit visit =
        visitRepository
            .findById(visit_id)
            .orElseThrow(() -> new VisitNotFoundException("There is no visit available"));
    Patient patient = visit.getPatient();
    LabFile dbFile = labFileService.storeFile(file, patient, visit);

    String fileDownloadUri =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(dbFile.getId())
            .toUriString();

    return new UploadFileResponse(
        dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
  }

  @PostMapping("/uploadMultipleFiles")
  public List<UploadFileResponse> uploadMultipleFiles(
      @RequestParam("files") MultipartFile[] files, @RequestParam("visit_id") Long visit_id) {
    return Arrays.stream(files)
        .map(file -> uploadFile(file, visit_id))
        .collect(Collectors.toList());
  }

  @GetMapping("/downloadFile/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    // Load file from database
    LabFile dbFile = labFileService.getFile(fileId);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(dbFile.getFileType()))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + dbFile.getFileName() + "\"")
        .body(new ByteArrayResource(dbFile.getData()));
  }
}
