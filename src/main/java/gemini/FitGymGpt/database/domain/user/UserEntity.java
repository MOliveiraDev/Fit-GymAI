package gemini.FitGymGpt.database.domain.user;

import gemini.FitGymGpt.database.domain.fitcenter.GymCenterEntity;
import gemini.FitGymGpt.enums.UserRoles;
import gemini.FitGymGpt.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "users_tb")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false, name = "username")
    @NotBlank(message = "Nome do usuário não pode ser vazio")
    private String username;

    @Column(nullable = false, unique = true, name = "email")
    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @Column(nullable = false, name = "password")
    @NotBlank(message = "Senha não pode ser vazia")
    private String password;

    @Column(name = "birthday_date", nullable = false)
    private Date birthdayDate;

    @Column(nullable = false, name = "gender")
    private String gender;

    @Column(name = "age", nullable= false)
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_center_id")
    private GymCenterEntity gymCenterEntity;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private UserRoles userRoles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_status")
    private UserStatus userStatus;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + userRoles.name());
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
