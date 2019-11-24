package christou.aristotelis.medirec.service;

import christou.aristotelis.medirec.entities.LabFile;
import christou.aristotelis.medirec.entities.Patient;
import christou.aristotelis.medirec.entities.Visit;
import christou.aristotelis.medirec.exception.FileStorageException;
import christou.aristotelis.medirec.exception.FileNotFoundException;
import christou.aristotelis.medirec.repositories.LabFileRepository;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LabFileService {
  private final LabFileRepository labFileRepository;

  public LabFileService(LabFileRepository labFileRepository) {
    this.labFileRepository = labFileRepository;
  }

  public LabFile storeFile(MultipartFile file, Patient patient, Visit visit) {
    // Normalize filename
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      // Check for filename invalid characters
      if (fileName.contains("..")) {
        throw new FileStorageException("The filename is invalid " + fileName);
      }

      LabFile labFile = new LabFile(fileName, file.getContentType(), file.getBytes(), patient, visit);
      return labFileRepository.save(labFile);
    } catch (IOException ex) {
      throw new FileStorageException("Couldn't save file " + fileName + ". Please try again!", ex);
    }
  }

  public LabFile getFile(String fileId) {
    return labFileRepository.findById(fileId).orElseThrow(() ->
      new FileNotFoundException("File not found with id " + fileId)
    );
  }
}
