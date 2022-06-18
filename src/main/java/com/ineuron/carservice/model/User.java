package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements UserDetails,Serializable  {

    private static final long serialVersionUID = 8771794405362859850l;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    @CreatedBy
    @JsonIgnore
    private UUID createdBy;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    @LastModifiedBy
    @JsonIgnore
    private UUID updatedBy;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    @JsonIgnore
    private String mobileNumber;

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "is_account_expired", nullable = false)
    @JsonIgnore
    private Boolean isAccountExpired;

    @Column(name = "is_account_locked", nullable = false)
    @JsonIgnore
    private Boolean isAccountLocked;

    @Column(name = "is_credential_expired", nullable = false)
    @JsonIgnore
    private Boolean isCredentialExpired;

    @Column(name = "is_enabled", nullable = false)
    @JsonIgnore
    private boolean isEnabled;

    @Column(name = "last_login_date")
    @JsonIgnore
    private LocalDateTime lastLoginDate;

    @Column(name = "last_password_changed_at")
    @JsonIgnore
    private LocalDateTime lastPasswordChangedAt;

    @Column(name = "last_locked_out_at")
    @JsonIgnore
    private LocalDateTime lastLockedOutAt;

    @Column(name = "failed_password_attempts", nullable = false)
    @JsonIgnore
    private Integer failedPasswordAttempts;

    @OneToOne
    @JoinColumn(name ="role_id")
    private Role role;

    @Column(name = "fcm_key")
    private String fcmKey;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        grantedAuthorities.add(authority);
        return grantedAuthorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !isCredentialExpired;
    }

}
