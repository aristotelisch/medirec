package christou.aristotelis.medirec.service;

import lombok.Data;

@Data
public class LabFileDTO {
  private String id;
  private String fileName;
  private String fileType;
  private byte[] data;
}
