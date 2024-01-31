package ma.enset.hospital.repositories;

import ma.enset.hospital.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Optional<Medecin> findByNom(String nom);
}
