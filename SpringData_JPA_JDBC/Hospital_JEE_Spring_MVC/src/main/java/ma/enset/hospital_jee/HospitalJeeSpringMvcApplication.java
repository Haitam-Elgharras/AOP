package ma.enset.hospital_jee;

import lombok.AllArgsConstructor;
import ma.enset.hospital_jee.entities.Patient;
import ma.enset.hospital_jee.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class HospitalJeeSpringMvcApplication implements CommandLineRunner {

    private final PatientRepository patientRepository;

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
}
