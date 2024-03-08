package ma.enset.hospital_jee.security.service;

import lombok.AllArgsConstructor;
import ma.enset.hospital_jee.security.entities.AppRole;
import ma.enset.hospital_jee.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    IAccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if (appUser == null) throw new UsernameNotFoundException("Invalid user");

        String[] roles = appUser.getRoles().stream().map(AppRole::getRole).toArray(String[]::new);

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }


}
