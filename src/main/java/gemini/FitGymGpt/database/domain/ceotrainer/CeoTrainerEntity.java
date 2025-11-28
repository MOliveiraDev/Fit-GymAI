package gemini.FitGymGpt.database.domain.ceotrainer;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GymCenterEntity> gymCenters = new HashSet<>();

    @Column(name = "years_experience", nullable = false)
    private Integer yearsExperience;
}
