package ma.enset.hospital_jee.security.service;

import groovy.lang.Lazy;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ma.enset.hospital_jee.security.entities.AppRole;
import ma.enset.hospital_jee.security.entities.AppUser;
import ma.enset.hospital_jee.security.repo.AppRoleRepository;
import ma.enset.hospital_jee.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String confirmedPassword, String email) {
       // verify if the user already exists
        AppUser user = appUserRepository.findByEmail(username);
        if(user!=null) throw new RuntimeException("User already exists");
        // verify if the password and the confirmed password are the same
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Password does not match");

        AppUser appUser = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        return appUserRepository.save(appUser);

    }

    @Override
    public AppRole addNewRole(String role) {
      // verify if the role already exists
        AppRole appRole = appRoleRepository.findByRole(role);
        if(appRole!=null) throw new RuntimeException("Role already exists");
        appRole = AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        // verify if the user and the role exist
        AppUser appUser = appUserRepository.findByEmail(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRole(role);
        if(appRole==null) throw new RuntimeException("Role not found");

        // verify if the user already has the role
        if(appUser.getRoles().contains(appRole)) throw new RuntimeException("User already has the role");

        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String username, String role) {
        // verify if the user and the role exist
        AppUser appUser = appUserRepository.findByEmail(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRole(role);
        if(appRole==null) throw new RuntimeException("Role not found");

        // verify if the user already has the role
        if(!appUser.getRoles().contains(appRole)) throw new RuntimeException("User does not have the role");

        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByEmail(username);
    }
}
