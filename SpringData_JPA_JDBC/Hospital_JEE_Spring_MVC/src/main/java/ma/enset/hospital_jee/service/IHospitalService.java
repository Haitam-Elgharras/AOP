package ma.enset.hospital_jee.service;

import ma.enset.hospital_jee.entities.Consultation;
import ma.enset.hospital_jee.entities.Medecin;
import ma.enset.hospital_jee.entities.Patient;
import ma.enset.hospital_jee.entities.RendezVous;

import java.util.List;

public interface IHospitalService {
    Patient savePatient(Patient p);
    Medecin saveMedecin(Medecin m);
    RendezVous saveRendezVous(RendezVous r);
    Consultation saveConsultation(Consultation c);

    Medecin findMedecinByName(String name);

    List<Patient> findAllPatients();
}
