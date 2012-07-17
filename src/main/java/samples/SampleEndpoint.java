package samples;

import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.server.endpoint.annotation.*;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

@Endpoint
public class SampleEndpoint {

    @PayloadRoot(localPart = "orderRequest", namespace = "http://samples")
    @Namespace(prefix = "s", uri = "http://samples")
    @ResponsePayload
    public StreamSource foo(@XPathParam("/s:orderRequest/@id") int orderId) throws IOException {
        return new StreamSource(readFileToString(new ClassPathResource("response.xml").getFile()));
    }

}