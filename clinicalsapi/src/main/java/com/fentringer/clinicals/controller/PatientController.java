package com.fentringer.clinicals.controller;

import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.PatientRepository;
import com.fentringer.clinicals.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping(value = "/patients/{id}")
    public Patient getPatientById(@PathVariable("id") int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/patients")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping("/patients/{id}/analyze")
    public Patient analyzePatient(@PathVariable("id") int id) {
        return patientService.analyzePatient(id);
    }

}
