package tokyo.chupaaaaaaan;

import java.io.InputStream;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.BodySubscribers;
import java.net.http.HttpResponse.ResponseInfo;
import java.util.function.Supplier;

/**
 * ShapeFileHandler
 * Type W :: JSON Model
 */
public class ShapeFileHandler<W> implements HttpResponse.BodyHandler<Supplier<W>> {

    // private final Class<W> clazz;

    // public ShapeFileHandler (Class<W> clazz) {
    //     this.clazz = clazz;
    // }

    @Override
    public BodySubscriber<Supplier<W>> apply(ResponseInfo responseInfo) {
        
        // return asJSON(clazz);
        
        return null;
    }

    // public BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
    //     BodySubscriber<InputStream> upstream = BodySubscribe

    //     return BodySubscribers.mapping(upstream, is -> toSupplierOfType(is, targetType));
    // }


    // public <W> Supplier<W> toSupplierOfType(InputStream is, Class<W> targetType) {
    //     return () -> {
    //         try(InputStream stream = is) {
                
    //         } 
    //     };
    // }

}
