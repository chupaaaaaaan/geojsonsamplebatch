package tokyo.chupaaaaaaan;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;
import java.util.function.Supplier;
import java.util.zip.GZIPInputStream;

/**
 * HttpExecutor
 */
public class GzBodyHandler implements BodyHandler<Supplier<InputStream>> {
    
    HttpResponse.BodySubscriber<Supplier<InputStream>> gzBodySubscriber(ResponseInfo responseInfo) {
        return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofInputStream(),
                                                    this::decodeGzipStreamSupplier);
    }

    private Supplier<InputStream> decodeGzipStreamSupplier(InputStream is) {
        return () -> {
            try {
                return new GZIPInputStream(is);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    @Override
    public BodySubscriber<Supplier<InputStream>> apply(ResponseInfo responseInfo) {
        return gzBodySubscriber(responseInfo);
    }

    
}
