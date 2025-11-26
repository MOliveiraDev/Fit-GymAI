package gemini.FitGymGpt.dto.gemini;

import java.util.List;

public record BodyStatsRequest(

        // Dados pessoais
        int age,
        String gender, // "MALE", "FEMALE", "OTHER"

        // Medidas corporais básicas
        double height, // em metros
        double weight, // em kg

        // Medidas de membros superiores
        double leftArm, // braço esquerdo em cm
        double rightArm, // braço direito em cm
        double leftForearm, // antebraço esquerdo em cm
        double rightForearm, // antebraço direito em cm

        // Medidas do tronco
        double chest, // peito em cm
        double waist, // cintura em cm
        double abdomen, // abdômen em cm
        double hip, // quadril em cm
        double shoulders, // ombros em cm

        // Medidas de membros inferiores
        double leftThigh, // coxa esquerda em cm
        double rightThigh, // coxa direita em cm
        double leftCalf, // panturrilha esquerda em cm
        double rightCalf, // panturrilha direita em cm

        // Composição corporal
        String biotype, // "ECTOMORPH", "MESOMORPH", "ENDOMORPH"
        Double bodyFatPercentage, // percentual de gordura (opcional)
        Double muscleMass, // massa muscular em kg (opcional)

        // Objetivos e experiência
        String fitnessGoal, // "WEIGHT_LOSS", "MUSCLE_GAIN", "MAINTENANCE", "ATHLETIC_PERFORMANCE"
        String experienceLevel, // "BEGINNER", "INTERMEDIATE", "ADVANCED"

        // Condições de saúde e limitações
        List<String> injuries, // lesões atuais ou passadas
        List<String> medicalConditions, // condições médicas (diabetes, hipertensão, etc)
        List<String> physicalLimitations, // limitações físicas

        // Disponibilidade e preferências
        int weeklyWorkoutDays, // dias disponíveis para treinar por semana
        String preferredWorkoutTime, // "MORNING", "AFTERNOON", "EVENING"
        List<String> availableEquipment, // equipamentos disponíveis
        List<String> exercisePreferences, // preferências de exercícios
        List<String> exerciseRestrictions // exercícios a evitar
) {}
