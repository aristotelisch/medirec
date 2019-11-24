package christou.aristotelis.medirec;

import christou.aristotelis.medirec.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class MedirecApplication {

  public static void main(String[] args) {
    SpringApplication.run(MedirecApplication.class, args);
  }
}
