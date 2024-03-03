package ma.enset.hospital_jee;

import lombok.AllArgsConstructor;
import ma.enset.hospital_jee.entities.Patient;
import ma.enset.hospital_jee.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class HospitalJeeSpringMvcApplication implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HospitalJeeSpringMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // create some patients and save them to the database
        Patient patient1 = new Patient(null, "ahmed", new Date(), false, null);
        Patient patient2 = new Patient(null, "youssef", new Date(), false, null);
        Patient patient3 = new Patient(null, "mohamed", new Date(), false, null);
        Patient patient4 = new Patient(null, "fatima", new Date(), false, null);

        patientRepository.saveAll(List.of(patient1, patient2, patient3, patient4));

    }

    @Bean
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
}
