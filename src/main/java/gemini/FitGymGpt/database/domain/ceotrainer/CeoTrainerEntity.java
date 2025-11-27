package gemini.FitGymGpt.database.domain.ceotrainer;

import gemini.FitGymGpt.database.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ceo_trainers_tb")
public class CeoTrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ceo_trainer_id", nullable = false)
    private Long ceoTrainerId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "years_experience", nullable = false)
    private Integer yearsExperience;
}
