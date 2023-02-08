package com.fentringer.clinicals.controller;

import com.fentringer.clinicals.model.ClinicalData;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    Map<String, String> filters = new HashMap<>();

    @GetMapping(value = "/patients")
    public List<Patient> getPatients() {
        return repository.findAll();
    }

    @GetMapping(value = "/patients/{id}")
    public Patient getPatientById(@PathVariable("id") int id) {
        return repository.findById(id).get();
    }

    @PostMapping("/patients")
    public Patient savePatient(@RequestBody Patient patient) {
        return repository.save(patient);
    }

    @GetMapping(value = "/patients/analyze/{id}")
    public Patient analyze(@PathVariable("id") int id) {
        Patient patient = repository.findById(id).get();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);

        for (ClinicalData eachEntry : duplicateClinicalData) {
            if (filters.containsKey(eachEntry.getComponentName())) {
                clinicalData.remove(eachEntry);
                continue;
            } else {
                filters.put(eachEntry.getComponentName(), null);
            }

            if(eachEntry.getComponentName().equals("hw")) {
                String[] heightAndWeight = eachEntry.getComponentName().split("/");
                if (heightAndWeight != null && heightAndWeight.length > 1) {
                    float heightInMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
                    float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMetres * heightInMetres);
                    ClinicalData bmiData = new ClinicalData();
                    bmiData.setComponentName("bmi");
                    bmiData.setComponentValue(Float.toString(bmi));
                    clinicalData.add(bmiData);
                }
            }

        }
        filters.clear();
        return patient;
    }

}
