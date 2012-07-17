package samples;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SampleEndpointTest {

    @Test
    public void should_return_expected_response() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://localhost:9090/springwsdemo/ws");
        try {
            int status = client.executeMethod(post);
            if (status != 200) {
                throw new RuntimeException();
            }

            String response = post.getResponseBodyAsString();
            assertThat(response, is(readFileToString(new ClassPathResource("response.xml").getFile())));
        } finally {
            post.releaseConnection();
        }
    }
}
