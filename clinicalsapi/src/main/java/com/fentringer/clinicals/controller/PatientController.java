package com.fentringer.clinicals.controller;

import com.fentringer.clinicals.dto.PatientDto;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/patients")
    public List<PatientDto> getPatients() {
        return patientService.getPatients().stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/patients/{id}")
    public PatientDto getPatientById(@PathVariable("id") int id) {
        return modelMapper.map(patientService.getPatientById(id), PatientDto.class);
    }

    @PostMapping("/patients")
    public PatientDto savePatient(@RequestBody PatientDto patientDto) {
        return modelMapper.map(patientService.savePatient(modelMapper.map(patientDto, Patient.class)), PatientDto.class);
    }

    @GetMapping("/patients/{id}/analyze")
    public PatientDto analyzePatient(@PathVariable("id") int id) {
        return modelMapper.map(patientService.analyzePatient(id), PatientDto.class);
    }

}
