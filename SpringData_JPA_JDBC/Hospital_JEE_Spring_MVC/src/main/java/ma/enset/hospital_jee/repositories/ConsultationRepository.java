package ma.enset.hospital_jee.repositories;


import ma.enset.hospital_jee.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
