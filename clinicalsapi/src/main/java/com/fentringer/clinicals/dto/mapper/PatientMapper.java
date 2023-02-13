package com.fentringer.clinicals.dto.mapper;

import com.fentringer.clinicals.dto.PatientDto;
import com.fentringer.clinicals.model.Patient;
import org.modelmapper.ModelMapper;

public class PatientMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static PatientDto convertToPatientDto(final Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }
}
