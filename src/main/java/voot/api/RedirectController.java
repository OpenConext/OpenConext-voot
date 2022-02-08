package voot.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RedirectController {

    @GetMapping("/health")
    public ResponseEntity health() throws URISyntaxException {
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(new URI("/internal/health")).build();
    }

    @GetMapping("/info")
    public ResponseEntity info() throws URISyntaxException {
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(new URI("/internal/info")).build();
    }
}
