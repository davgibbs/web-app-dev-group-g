package ie.dcu.library.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class TokenResponse {
    private String token;
    private List<String> roles;
}
