package wg.user.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new ResponseStatusException(
                HttpStatus.valueOf(response.status()),
                methodKey + "Reason: " + response.reason());
    }
}
