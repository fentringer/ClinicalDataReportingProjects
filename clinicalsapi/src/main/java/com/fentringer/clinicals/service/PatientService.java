package com.fentringer.clinicals.service;

import com.fentringer.clinicals.dto.PatientDto;
import com.fentringer.clinicals.dto.mapper.PatientMapper;
import com.fentringer.clinicals.messages.ErrorMessages;
import com.fentringer.clinicals.model.ClinicalData;
import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository repository;

    private final ClinicalDataService clinicalDataService;

    public List<Patient> getPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientById(final int id) {
        return repository.findById(id);
    }

    public Patient savePatient(final Patient patient) {
        return repository.save(patient);
    }

    /**
     * Method to retrieve a patient with their updated clinical information.
     *
     * @param id the ID of the patient
     * @return the patient DTO with updated clinical data
     * @throws IllegalArgumentException if the patient is not found or if the clinical data is null
     */
    public PatientDto getPatientWithUpdatedClinicalData(final int id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.PATIENT_NOT_FOUND.getKeyMessage()));

        List<ClinicalData> updatedClinicalData = clinicalDataService.updateAllClinicalData(patient.getClinicalData());
        patient.setClinicalData(updatedClinicalData);

        return PatientMapper.convertToPatientDto(patient);
    }

    public void deletePatient(final int id) {
        repository.deleteById(id);
    }


}
