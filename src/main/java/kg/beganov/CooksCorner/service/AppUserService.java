package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.entity.AppUser;

public interface AppUserService {
    AppUser findById(Long id);
}
