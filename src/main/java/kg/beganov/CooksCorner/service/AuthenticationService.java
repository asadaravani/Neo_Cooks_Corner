package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.request.LoginRequest;
import kg.beganov.CooksCorner.dto.request.RegisterRequest;
import kg.beganov.CooksCorner.dto.response.AuthenticationResponse;
import kg.beganov.CooksCorner.exception.ConfirmationTokenExpiredException;
import kg.beganov.CooksCorner.exception.InvalidDataProvidedException;
import kg.beganov.CooksCorner.exception.UserNotFoundException;
import kg.beganov.CooksCorner.exception.UserNotVerifiedException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(LoginRequest loginRequest) throws UserNotFoundException, UserNotVerifiedException, InvalidDataProvidedException;

    String register(RegisterRequest registerRequest);

    String confirmToken(String token) throws ConfirmationTokenExpiredException;
}
