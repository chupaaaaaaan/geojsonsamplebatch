package tokyo.chupaaaaaaan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/gzip"))
            .build();

        InputStream is = null;

        try {

            is = client.send(request,new GzBodyHandler()).body().get();
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                
                var content = sb.toString();

                System.out.println(content);
                
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }


}
