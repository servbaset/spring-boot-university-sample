package app.raiko.university.integration;

import java.util.Map;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseIntegrationTest {

  static GenericContainer<?> minio = new GenericContainer<>(
      DockerImageName.parse("minio/minio:latest"))
      .withCommand("server /data")
      .withExposedPorts(9000)
      .withEnv(Map.of(
          "MINIO_ACCESS_KEY", "aslfdjaslfjalsfd",
          "MINIO_SECRET_KEY", "lksajfla;df"
      ));

  @DynamicPropertySource
  static void redisProperties(DynamicPropertyRegistry registry) {
    minio.start();
    registry.add("app.raiko.storage.endpoint",
        () -> "http://%s:%S".formatted(minio.getHost(), minio.getFirstMappedPort()));
    registry.add("app.raiko.storage.accessKey",
        () -> "aslfdjaslfjalsfd");
    registry.add("app.raiko.storage.secretKey",
        () -> "lksajfla;df");
  }

}
