package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.config.JavaMailSenderConfig;
import kg.beganov.CooksCorner.config.JwtUtil;
import kg.beganov.CooksCorner.dto.request.LoginRequest;
import kg.beganov.CooksCorner.dto.request.RegisterRequest;
import kg.beganov.CooksCorner.dto.response.AuthenticationResponse;
import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.entity.ConfirmationToken;
import kg.beganov.CooksCorner.enums.Role;
import kg.beganov.CooksCorner.exception.*;
import kg.beganov.CooksCorner.repository.AppUserRepository;
import kg.beganov.CooksCorner.service.AuthenticationService;
import kg.beganov.CooksCorner.service.ConfirmationTokenService;
import kg.beganov.CooksCorner.service.EmailSenderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    AppUserRepository appUserRepository;
    PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;
    AuthenticationManager authenticationManager;
    EmailSenderService emailSenderService;
    ConfirmationTokenService confirmationTokenService;
    JavaMailSenderConfig mailSenderConfig;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) throws UserNotFoundException, UserNotVerifiedException, InvalidDataProvidedException {
        String emailToLowercase = loginRequest.getEmail().toLowerCase();
        AppUser user = appUserRepository.findUserByEmail(emailToLowercase)
                .orElseThrow(UserNotFoundException::new);
        if(!user.isEmailVerified()){
            ConfirmationToken confirmationToken = new ConfirmationToken();
            String link = generateLink(confirmationToken, user);
            emailSenderService.send(
                    user.getEmail(),
                    emailSenderService.buildEmail(user.getUsername(), link));

            throw new UserNotVerifiedException();
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            emailToLowercase,
                            loginRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new InvalidDataProvidedException();
        }
        String userEmail = user.getEmail();
        var jwtToken = jwtUtil.generateToken(userEmail);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public String register(RegisterRequest registerRequest) throws UserAlreadyExistException, InvalidDataProvidedException {
        if(isEmailTaken(registerRequest.getEmail())){
            throw new UserAlreadyExistException();
        }
        String confirmedEmail = registerRequest.getEmail().toLowerCase();
        AppUser appUser = new AppUser();
        appUser.setName(registerRequest.getName());
        appUser.setEmail(confirmedEmail);
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setRole(Role.USER);
        appUserRepository.save(appUser);
        ConfirmationToken confirmationToken = new ConfirmationToken();
        String link = generateLink(confirmationToken, appUser);
        emailSenderService.send(
                confirmedEmail,
                emailSenderService.buildEmail(registerRequest.getName(), link));
        return "We have sent you a link to " +confirmedEmail+", please check your email";
    }

    @Override
    public String confirmToken(String token) throws ConfirmationTokenExpiredException {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(InvalidDataProvidedException::new);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ConfirmationTokenExpiredException();
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenExpiredException();
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserRepository.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "Confirmed";
    }
    private String generateLink(ConfirmationToken confirmationToken, AppUser appUser) {
        String token = UUID.randomUUID().toString();
        confirmationToken.setToken(token);
        confirmationToken.setAppUser(appUser);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(5L));
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return "http://" +mailSenderConfig.getLinkApi()+":8080/api/authentication/confirm?token=" + token;

    }
    public boolean isEmailTaken(String email){
        return appUserRepository.existsByEmail(email);
    }

}
