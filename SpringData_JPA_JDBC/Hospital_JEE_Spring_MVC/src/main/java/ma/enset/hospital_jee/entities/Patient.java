package ma.enset.hospital_jee.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity
//@Data // will generate getters and setters
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4, max = 20)
    private String nom;

    @NotBlank
    @Size(min = 4, max = 20)
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private boolean malade;
    private int score;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private Collection<RendezVous> rendezVous;

}
