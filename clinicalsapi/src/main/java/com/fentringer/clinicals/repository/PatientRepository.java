package com.fentringer.clinicals.repository;

import com.fentringer.clinicals.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
