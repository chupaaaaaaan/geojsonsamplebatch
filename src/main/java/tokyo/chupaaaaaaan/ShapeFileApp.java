package tokyo.chupaaaaaaan;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Hello world!
 *
 */
public class ShapeFileApp
{
    public static final Logger logger = Logger.getLogger(ShapeFileApp.class.toString());
    public static Handler handler = new ConsoleHandler();
    
    public static void main( String[] args ) {

        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        var uri = URI.create(
                "https://data.bodik.jp/dataset/d4b9a284-58c0-4559-9e48-b7c83a4275f0/resource/28aa8f13-7cf0-4dc5-9dbf-a77f58c3f2c8/download/doshakeikai_dosekiryu_20210326.zip");

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
            .uri(uri)
            .build();

        var downloadFile = Path.of(uri.getPath()).getFileName();
        var downloadDir = Paths.get(System.getProperty("java.io.tmpdir"));
        var path = downloadDir.resolve(downloadFile);
        
        logger.info("download file: " + path);
        
        try {
            client.send(request, BodyHandlers.ofFile(path)).body();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        var shapeFilePath = ExtractZip.withShapeFileName(path);

        Shape2GeoJSON.translate(shapeFilePath);





    }

}
