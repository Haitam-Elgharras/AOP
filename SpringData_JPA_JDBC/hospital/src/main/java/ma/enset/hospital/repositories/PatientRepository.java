package ma.enset.hospital.repositories;

import ma.enset.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByNom(String nom);
}
