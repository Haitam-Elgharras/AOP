package ma.enset.hospital_jee.web;


import ma.enset.hospital_jee.entities.Consultation;
import ma.enset.hospital_jee.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsultationController {
    private ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationController(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @GetMapping("/consultations")
    public List<Consultation> consultations() {
        return consultationRepository.findAll();
    }
}
