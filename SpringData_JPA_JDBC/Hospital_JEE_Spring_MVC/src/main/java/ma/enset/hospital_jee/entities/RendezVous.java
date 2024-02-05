package ma.enset.hospital_jee.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateRendezVous;

    @Enumerated(EnumType.STRING)
    private StatusRDV status;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;

    @ManyToOne
    private Medecin medecin;
    // the mapped here means that the rendezVous will be created first
    // the id of the rendezVous will be used as a foreign key in the consultation table
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;

}
