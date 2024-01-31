package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean // This annotation is used to declare a bean in the application context.
	// and it will help us to inject the IHospitalService interface in the start method.
	CommandLineRunner start(
			IHospitalService hospitalService
			) {
		return args -> {
			Stream.of("Mohamed","Ahmed","Omar").forEach(p->{
				Patient patient = new Patient();
				patient.setNom(p);
				patient.setDateNaissance(new Date());
				patient.setMalade(Math.random()>0.5);
				hospitalService.savePatient(patient);
			});

			Stream.of("Aymane","Kamal","Omar").forEach(m->{
				Medecin medecin = new Medecin();
				medecin.setNom(m);
				medecin.setEmail(m+"@gmail.com");
				medecin.setSpecialite(Math.random()>0.5?"Dentiste":"Ophtalmologue");
				hospitalService.saveMedecin(medecin);
			});

			Patient patient = hospitalService.findAllPatients().get(0);
			Medecin medecin = hospitalService.findMedecinByName("Aymane");

			RendezVous rv = new RendezVous();

			rv.setDateRendezVous(new Date());
			rv.setMedecin(medecin);
			rv.setPatient(patient);
			rv.setStatus(StatusRDV.PENDING);
			hospitalService.saveRendezVous(rv);

			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rv);
			consultation.setRapport("Rapport de la consultation...");
			hospitalService.saveConsultation(consultation);
		};
	}

}
