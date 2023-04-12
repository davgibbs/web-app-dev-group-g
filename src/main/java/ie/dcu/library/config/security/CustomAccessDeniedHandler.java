package ie.dcu.library.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.dcu.library.model.Error;
import ie.dcu.library.model.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.io.OutputStream;

public class CustomAccessDeniedHandler  implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        Error error = Error.builder()
                .status(HttpServletResponse.SC_FORBIDDEN)
                .code(ErrorCode.USER_FORBIDDEN.getCodeName())
                .message(accessDeniedException.getMessage()).build();
        final ObjectMapper mapper = new ObjectMapper();
        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, error);
        out.flush();
    }
}
