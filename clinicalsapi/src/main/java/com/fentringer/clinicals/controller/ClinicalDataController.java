package com.fentringer.clinicals.controller;

import com.fentringer.clinicals.model.ClinicalData;
import com.fentringer.clinicals.dto.ClinicalDataDTO;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.ClinicalDataRepository;
import com.fentringer.clinicals.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataDTO clinicalDataDTO) {
        Patient patient = patientRepository.findById(clinicalDataDTO.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(clinicalDataDTO.getComponentName());
        clinicalData.setComponentValue(clinicalDataDTO.getComponentValue());
        clinicalData.setPatient(patient);

        return clinicalDataRepository.save(clinicalData);
    }
}
