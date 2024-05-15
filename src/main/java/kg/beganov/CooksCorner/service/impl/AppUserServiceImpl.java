package kg.beganov.CooksCorner.service.impl;

import kg.beganov.CooksCorner.entity.AppUser;
import kg.beganov.CooksCorner.exception.UserNotFoundException;
import kg.beganov.CooksCorner.repository.AppUserRepository;
import kg.beganov.CooksCorner.service.AppUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppUserServiceImpl implements AppUserService {
    AppUserRepository appUserRepository;

    @Override
    public AppUser findById(Long id) {
        return appUserRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
