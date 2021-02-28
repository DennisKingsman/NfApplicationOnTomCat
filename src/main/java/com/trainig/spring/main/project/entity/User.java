package com.trainig.spring.main.project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class User implements UserDetails {

    private static final String MESSAGE = "invalid length";

    private Long userId;
    @Size(min = 2, max = 15, message = MESSAGE)
    private String userName;
    @Size(min = 3, max = 100, message = MESSAGE)
    private String userPassword;
    @Size(min = 3, max = 100, message = MESSAGE)
    private String passwordConfirm;
    private Set<Role> roles;

    public User() {
    }

    public User(User user) {
        this(user.getUserId(), user.getUsername(), user.getPassword(), user.getRoles());
    }

    public User(Long userId,
                @Size(min = 2, max = 15, message = MESSAGE) String userName,
                @Size(min = 3, max = 15, message = MESSAGE) String userPassword,
                Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public User(String userName, String userPassword, String passwordConfirm, Set<Role> roles) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
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

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            User user = (User) obj;
            return user.getUserId().equals(userId) &&
                    user.getPassword().equals(userPassword) &&
                    user.getUsername().equals(userName);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                '}';
    }

}
