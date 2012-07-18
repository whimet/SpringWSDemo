package samples;

import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.XPathParam;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

@Endpoint
public class SampleEndpoint {

    @PayloadRoot(localPart = "ServiceRequest", namespace = "http://samples")
    @Namespace(prefix = "s", uri = "http://samples")
    public StreamSource foo(@XPathParam("/s:ServiceRequest/Service/@Name") String name) throws IOException {
        return new StreamSource(readFileToString(new ClassPathResource("response.xml").getFile()));
    }

}