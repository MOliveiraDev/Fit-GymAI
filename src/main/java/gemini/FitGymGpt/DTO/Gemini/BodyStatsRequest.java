package gemini.FitGymGpt.dto.gemini;

import lombok.Data;

@Data
public class BodyStatsRequest {

    private int age;
    private double height;
    private double weight;
    private double arm;
    private double chest;
    private double waist;
    private double abdomen;
    private double hip;
    private double thigh;
    private double calf;
    private String biotype;
}
