package com.fentringer.clinicals;

import com.fentringer.clinicals.model.Patient;
import com.fentringer.clinicals.repository.PatientRepository;
import com.fentringer.clinicals.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepository repository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void getPatientsTest() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());

        when(repository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getPatients();
        assertEquals(2, result.size());
    }

    @Test
    void getPatientByIdTest() {
        Patient patient = new Patient();

        when(repository.findById(1)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.getPatientById(1);
        assertTrue(result.isPresent());
        assertEquals(patient, result.get());
    }

    @Test
    void savePatientTest() {
        Patient patient = new Patient();

        when(repository.save(patient)).thenReturn(patient);

        Patient result = patientService.savePatient(patient);
        assertEquals(patient, result);
    }

    @Test
    void deletePatient() {
        int id = 1;
        Patient patient = new Patient();
        patient.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(patient));

        patientService.deletePatient(id);

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertFalse(repository.findById(id).isPresent());
    }


}