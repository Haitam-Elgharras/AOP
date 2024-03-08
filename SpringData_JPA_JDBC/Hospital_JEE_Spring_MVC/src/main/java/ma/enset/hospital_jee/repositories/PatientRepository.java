package ma.enset.hospital_jee.repositories;


import ma.enset.hospital_jee.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // it's obligatory to add a parameter of type Pageable when the method returns a Page
    Page<Patient> findPatientByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String keyword,String keyword1, Pageable pageable);
}
