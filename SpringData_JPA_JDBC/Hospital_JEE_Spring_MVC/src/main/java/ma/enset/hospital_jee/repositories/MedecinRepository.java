package ma.enset.hospital_jee.repositories;

import ma.enset.hospital_jee.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Optional<Medecin> findByNom(String nom);
}
