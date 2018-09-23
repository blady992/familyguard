package pl.aagenda.familyguard.datastorage.web.rest.error;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 8057168465468919627L;

    private final String message;
}
