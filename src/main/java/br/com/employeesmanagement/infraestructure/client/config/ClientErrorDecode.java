package br.com.employeesmanagement.infraestructure.client.config;

import br.com.employeesmanagement.infraestructure.exception.ClientApiFeingException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Component
public class ClientErrorDecode implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String methodName, Response response) {
        if (isHttpResponseBetween400And499(response)) {
            return new ClientApiFeingException("Client API error: " + response.status());
        } else {
            byte[] bodyBytes = StreamUtils.copyToByteArray(response.body().asInputStream());
            final String error = new String(bodyBytes, StandardCharsets.UTF_8);
            throw new ClientApiFeingException(error);
        }
    }

    private static boolean isHttpResponseBetween400And499(Response response) {
        return response.status() >= 400 && response.status() <= 499;
    }

}
