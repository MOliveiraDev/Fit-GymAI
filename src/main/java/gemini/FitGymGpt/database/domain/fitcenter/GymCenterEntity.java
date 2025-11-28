package gemini.FitGymGpt.database.domain.fitcenter;

import gemini.FitGymGpt.database.domain.ceotrainer.CeoTrainerEntity;
import gemini.FitGymGpt.database.domain.user.UserEntity;
import gemini.FitGymGpt.enums.GymStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "gym_centers_tb")
public class GymCenterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_center_id")
    private Long gymCenterId;

    @Column(nullable = false, name = "gym_name")
    @NotBlank(message = "Nome da academia não pode ser vazio")
    private String gymCenterName;

    @Column(name = "gym_description")
    private String gymDescription;

    @Column(name = "gym_phone")
    private String gymPhone;

    @Column(name = "gym_email")
    @Email(message = "Forneça um email corporativo")
    private String gymEmail;

    @Column(name = "CNPJ", nullable = false)
    private String taxId;

    @Column(name = "gym_website")
    private String gymWebsite;

    @Column(name = "gym_image_url")
    private String gymImageUrl;

    @Column(name = "gym_address", nullable = false)
    @NotBlank(message = "Endereço da academia não pode ser vazio")
    private String gymCenterAddress;

    @Column(name = "gym_open_time")
    @NotNull(message = "Horário de abertura é obrigatório")
    private LocalTime openingTime;

    @Column(name = "gym_close_time")
    @NotNull(message = "Horário de fechamento é obrigatório")
    private LocalTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private CeoTrainerEntity owner;

    @OneToMany(mappedBy = "gymCenterEntity")
    private Set<UserEntity> members = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "gym_status")
    private GymStatus gymStatus = GymStatus.CLOSED;
}

