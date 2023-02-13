package com.fentringer.clinicals.service;

import com.fentringer.clinicals.messages.ErrorMessages;
import com.fentringer.clinicals.model.ClinicalData;
import com.fentringer.clinicals.util.PatientMeasures;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClinicalDataService {

    private static final String BMI_COMPONENT_NAME = "hw";
    private static final String BMI = "bmi";
    private static final float KILOGRAMS_PER_POUND = 0.4536F;

    /**
     * Method to update all clinical data for a patient.
     *
     * @param clinicalData the list of clinical data for a patient
     * @return the updated list of clinical data
     * @throws NullPointerException if the clinical data is null
     */
    protected List<ClinicalData> updateAllClinicalData(final List<ClinicalData> clinicalData) {
        Objects.requireNonNull(clinicalData, ErrorMessages.CLINICAL_DATA_NULL.getKeyMessage());
        return clinicalData.stream()
                .map(this::manageClinicalData)
                .collect(Collectors.toList());
    }

    /**
     * Method to manage a single piece of clinical data.
     *
     * If the data is not a BMI component, it will be returned as is, otherwise calculate BMI and return.
     * @param data the clinical data to manage
     * @return the managed clinical data
     */
    private ClinicalData manageClinicalData(final ClinicalData data) {
        if (!BMI_COMPONENT_NAME.equals(data.getComponentName())) {
            return data;
        }
        return this.calculateBmiData(data.getComponentValue());
    }

    /**
     * Calculates the Body Mass Index (BMI) data for a patient based on their height and weight.
     *
     * @param heightAndWeight the height and weight of the patient in string format, separated by a "/"
     * @return a {@link ClinicalData} object containing the calculated BMI value
     * @throws IllegalArgumentException if the height and weight format is invalid or if the height or weight cannot be parsed as a number
     */
    private ClinicalData calculateBmiData(final String heightAndWeight) {
        PatientMeasures measures = PatientMeasures.buildMeasuresFromString(heightAndWeight);

        float heightInMetres = measures.getHeight() * KILOGRAMS_PER_POUND;
        float bmi = measures.getWeight() / (heightInMetres * heightInMetres);

        return new ClinicalData(BMI, String.valueOf(bmi));
    }
}

