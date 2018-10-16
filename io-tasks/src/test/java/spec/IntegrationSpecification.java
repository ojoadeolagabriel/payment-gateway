package spec;

import org.junit.Rule;
import org.testcontainers.containers.KafkaContainer;
import spock.lang.Specification;

public class IntegrationSpecification extends Specification {
    @Rule
    public KafkaContainer kafka = new KafkaContainer("4.1.2");
}
