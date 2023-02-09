package com.fentringer.clinicals.dto;

import com.fentringer.clinicals.model.ClinicalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private List<ClinicalData> clinicalData;
}
