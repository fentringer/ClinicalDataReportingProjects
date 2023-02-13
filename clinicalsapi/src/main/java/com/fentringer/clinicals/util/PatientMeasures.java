package com.fentringer.clinicals.util;

public final class PatientMeasures {
    private final float height;
    private final float weight;

    private PatientMeasures(final float height, final float weight) {
        this.height = height;
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public static PatientMeasures buildMeasuresFromString(final String heightAndWeight) {
        String[] values = heightAndWeight.split("/");
        if (values.length != 2) {
            throw new IllegalArgumentException("Invalid height and weight format");
        }
        try {
            float height = Float.parseFloat(values[0]);
            float weight = Float.parseFloat(values[1]);
            return new PatientMeasures(height, weight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format("The height: %s and weight: %s must be a number.", values[0], values[1]));
        }
    }
}
