package tokyo.chupaaaaaaan;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Shape2GeoJSON {

    public static void translate (Path shapeFilePath, Path geojsonFilePath) {

        try (BufferedWriter bw = Files.newBufferedWriter(geojsonFilePath,StandardCharsets.UTF_8)) {
            ShapefileDataStore shapeFile = new ShapefileDataStore(shapeFilePath.toUri().toURL());
            shapeFile.setCharset(Charset.forName("Shift_JIS"));
            SimpleFeatureCollection sfc = shapeFile.getFeatureSource().getFeatures();

            FeatureJSON featureJSON = new FeatureJSON();

            featureJSON.writeFeatureCollection(sfc, bw);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void translate (Path shapeFilePath) {
        translate(shapeFilePath,shapeFilePath.normalize().getParent().resolve(Path.of("out.geojson")));
    }

}
