package ma.enset.hospital_jee;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ma.enset.hospital_jee.entities.Patient;
import ma.enset.hospital_jee.repositories.PatientRepository;
import ma.enset.hospital_jee.security.service.IAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class HospitalJeeSpringMvcApplication implements CommandLineRunner {

    private final PatientRepository patientRepository;

    @Setter
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HospitalJeeSpringMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // create some patients and save them to the database
        Patient patient1 = new Patient(null, "ahmed","ahmed", new Date(), false,10, null);
        Patient patient2 = new Patient(null, "youssef","ahmed", new Date(), false,0, null);
        Patient patient3 = new Patient(null, "mohamed","ahmed", new Date(), false,20, null);
        Patient patient4 = new Patient(null, "fatima","ahmed", new Date(), false,60, null);

        for (int i =0;i<50;i++){
            Patient patient = new Patient(null, "ahmed"+i,"mohamed"+i, new Date(), false,i, null);
            patientRepository.save(patient);
        }

        // using builder
        Patient patient5 = Patient.builder()
                                   .nom("Imad")
                .prenom("Imad")
                                    .dateNaissance(new Date())
                                     .malade(false).score(60)
                                     .build();

        patientRepository.saveAll(List.of(patient1, patient2, patient3, patient4,patient5));

    }

//    @Bean for jdbc authentication
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager manager) {
        return args -> {
            if (!manager.userExists("admin2@gmail.com")) {
                manager.createUser(
                        User.withUsername("admin2@gmail.com")
                                .password(passwordEncoder.encode("1234"))
                                .roles("ADMIN","USER")
                                .build()
                );
                manager.createUser(
                        User.withUsername("user22@gmail.com")
                                .password(passwordEncoder.encode("1234"))
                                .roles("USER")
                                .build()
                );
                manager.createUser(
                        User.withUsername("user11@gmail.com")
                                .password(passwordEncoder.encode("1234"))
                                .roles("USER")
                                .build()
                );

            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerUserDetails(IAccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            // add user1 2 et admin avec useri@gmail.com et pass 1234
            accountService.addNewUser("user1", "1234", "1234", "user1@gmail.com");
            accountService.addNewUser("user2", "1234", "1234", "user2@gmail.com");
            accountService.addNewUser("admin", "1234", "1234", "admin@gmail.com");

            accountService.addRoleToUser("user1@gmail.com", "USER");
            accountService.addRoleToUser("user2@gmail.com", "USER");
            accountService.addRoleToUser("admin@gmail.com", "ADMIN");
            accountService.addRoleToUser("admin@gmail.com", "USER");


        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
