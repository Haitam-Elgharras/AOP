package ma.enset.hospital_jee.security.service;

import ma.enset.hospital_jee.security.entities.AppRole;
import ma.enset.hospital_jee.security.entities.AppUser;

public interface IAccountService {
    AppUser addNewUser(String username, String password, String confirmedPassword,String email);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleToUser(String username, String role);

    // necessary for spring security
    AppUser loadUserByUsername(String username);

}
