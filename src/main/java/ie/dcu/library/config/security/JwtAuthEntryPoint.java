package ie.dcu.library.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.dcu.library.model.Error;
import ie.dcu.library.model.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * @author Sheba Kurien
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Error error = Error.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .code(ErrorCode.USER_UNAUTHORIZED.getCodeName())
                .message( authException.getMessage()).build();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), error);
    }
}