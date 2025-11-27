package gemini.FitGymGpt.database.domain.personal;

import gemini.FitGymGpt.database.domain.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "personals_tb")
public class PersonalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pesonal_id")
    private Long personalId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "cref_number", nullable = false)
    @NotBlank(message = "CREF é obrigatório")
    private String crefNumber;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "years_experience")
    private Integer yearsExperience;

}
