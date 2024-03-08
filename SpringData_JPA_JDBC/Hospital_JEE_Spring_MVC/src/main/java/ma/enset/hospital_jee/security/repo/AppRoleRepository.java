package ma.enset.hospital_jee.security.repo;

import ma.enset.hospital_jee.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRole(String role);
}
