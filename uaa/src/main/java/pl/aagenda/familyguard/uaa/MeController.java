package pl.aagenda.familyguard.uaa;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/me")
public class MeController {

    @GetMapping
    public Object me(@AuthenticationPrincipal Object principal) {
        return principal;
    }
}
