package ie.dcu.library.model;
import ie.dcu.library.util.AppExceptionHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Error {

    private String code;
    private String message;
    private Integer status;

}
