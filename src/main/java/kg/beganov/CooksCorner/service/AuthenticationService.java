package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.request.LoginRequest;
import kg.beganov.CooksCorner.dto.request.RegisterRequest;
import kg.beganov.CooksCorner.dto.response.AuthenticationResponse;
import kg.beganov.CooksCorner.exception.*;

public interface AuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest)
            throws UserNotFoundException, UserNotVerifiedException, InvalidDataProvidedException;

    String register(RegisterRequest registerRequest) throws UserAlreadyExistException, InvalidDataProvidedException;

    String confirmToken(String token) throws ConfirmationTokenExpiredException;
}
