package tokyo.chupaaaaaaan;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipFile;

public class ExtractZip {

    public static Path withShapeFileName(Path zipFilePath) {

        ZipFile zipFile;
        try {
            zipFile = new ZipFile(zipFilePath.toFile());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        Path extractDir = zipFilePath.normalize().getParent();

        return zipFile.stream().map(zipEntry -> {
                    Path uncompressedFile = extractDir.resolve(Path.of(zipEntry.getName()));

                    if (zipEntry.isDirectory()) {
                        try {
                            Files.createDirectory(uncompressedFile);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    } else {
                        try(InputStream is = zipFile.getInputStream(zipEntry)) {
                            Files.copy(is, uncompressedFile, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    }
                    return uncompressedFile;})
                .filter(path -> path.toString().endsWith(".shp"))
                .findFirst().orElseThrow();
    }
}
