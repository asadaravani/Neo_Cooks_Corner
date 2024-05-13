package kg.beganov.CooksCorner.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.beganov.CooksCorner.dto.request.LoginRequest;
import kg.beganov.CooksCorner.dto.request.RegisterRequest;
import kg.beganov.CooksCorner.dto.response.AuthenticationResponse;
import kg.beganov.CooksCorner.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @Operation(summary = "User registration",
            description = "only checks if the email is taken")
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }

}
