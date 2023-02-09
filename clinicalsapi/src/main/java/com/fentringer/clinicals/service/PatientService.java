package com.fentringer.clinicals.service;

import com.fentringer.clinicals.model.ClinicalData;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository repository;
    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient analyzePatient(int id) {
        Patient patient = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> updatedClinicalData = clinicalData.stream()
                .map(this::updateData)
                .collect(Collectors.toList());
        patient.setClinicalData(updatedClinicalData);
        return patient;
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
        if (values.length > 1) {
            float heightInMetres = Float.parseFloat(values[0]) * 0.4536F;
            float bmi = Float.parseFloat(values[1]) / (heightInMetres * heightInMetres);
            ClinicalData bmiData = new ClinicalData();
            bmiData.setComponentName("bmi");
            bmiData.setComponentValue(Float.toString(bmi));
            return bmiData;
        }
        return null;
    }
}
