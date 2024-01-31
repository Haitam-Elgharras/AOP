package ma.enset.hospital.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Data // will generate getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private String id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private Collection<RendezVous> rendezVous;

}
