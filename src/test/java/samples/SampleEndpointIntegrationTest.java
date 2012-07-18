package samples;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SampleEndpointIntegrationTest {

    @Test
    public void should_return_expected_response() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://localhost:9090/SpringWSDemo-1.0/ws");
        post.setRequestEntity(new FileRequestEntity(new ClassPathResource("request.xml").getFile(), "text/xml"));
        try {
            int status = client.executeMethod(post);
            String response = post.getResponseBodyAsString();
            if (status != 200) {
                throw new RuntimeException(String.format("%s: %s", status, response));
            }

            assertThat(response, is(readFileToString(new ClassPathResource("response.xml").getFile())));
        } finally {
            post.releaseConnection();
        }
    }
}
