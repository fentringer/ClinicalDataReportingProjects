package com.fentringer.clinicals.controller;

import com.fentringer.clinicals.dto.PatientDto;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/patients")
    public ResponseEntity<List<PatientDto>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients().stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") int id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(value -> new ResponseEntity<>(modelMapper.map(value, PatientDto.class),HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto) {
        Patient savedPatient = patientService.savePatient(modelMapper.map(patientDto, Patient.class));

        return ResponseEntity.ok(modelMapper.map(savedPatient, PatientDto.class));
    }

    @GetMapping("/patients/{id}/analyze")
    public ResponseEntity<PatientDto> analyzePatient(@PathVariable("id") int id) {
        PatientDto analyzedPatient = patientService.analyzePatient(id);
        if (analyzedPatient == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(modelMapper.map(analyzedPatient, PatientDto.class));
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") int id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (!patient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
