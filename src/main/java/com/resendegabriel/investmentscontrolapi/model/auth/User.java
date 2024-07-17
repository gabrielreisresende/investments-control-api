package com.resendegabriel.investmentscontrolapi.model.auth;

import com.resendegabriel.investmentscontrolapi.model.Wallet;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserUpdateRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_USERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Wallet> wallets;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User fromRequest(UserRequest userRequest) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.password());
        return User.builder()
                .email(userRequest.email())
                .password(encryptedPassword)
                .role(Role.USER)
                .build();
    }

    public void updateData(UserUpdateRequest userRequest) {
        this.email = userRequest.email() != null ? userRequest.email() : this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getSimpleGrantedAuthority();
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
