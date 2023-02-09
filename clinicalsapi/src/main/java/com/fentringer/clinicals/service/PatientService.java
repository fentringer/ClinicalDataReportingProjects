package com.fentringer.clinicals.service;

import com.fentringer.clinicals.dto.PatientDto;
import com.fentringer.clinicals.model.ClinicalData;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final ModelMapper modelMapper;

    public List<Patient> getPatients() {
        return repository.findAll();
    }

    public Patient getPatientById(int id) {
        return repository.findById(id).get();
    }

    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    public PatientDto analyzePatient(int id) {
        Patient patient = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> updatedClinicalData = clinicalData.stream()
                .map(this::updateData)
                .collect(Collectors.toList());
        patient.setClinicalData(updatedClinicalData);
        return modelMapper.map(patient, PatientDto.class);
    }

    private ClinicalData updateData(ClinicalData data) {
        if (data.getComponentName().equals("hw")) {
            ClinicalData bmiData = calculateBmiData(data.getComponentValue());
            if (bmiData != null) {
                return bmiData;
            }
        }
        return data;
    }

    private ClinicalData calculateBmiData(String heightAndWeight) {
        String[] values = heightAndWeight.split("/");

        float heightInMetres = Float.parseFloat(values[0]) * 0.4536F;
        float bmi = Float.parseFloat(values[1]) / (heightInMetres * heightInMetres);

        ClinicalData bmiData = new ClinicalData();
        bmiData.setComponentName("bmi");
        bmiData.setComponentValue(Float.toString(bmi));

        return bmiData;

    }
}
