package com.azu.hospital.all_user_sevices.user_utils.base_user_service;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.bulding.Shift.arrange_shifts.entity.UserShiftsTable;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.humanResource.UserRating.entity.UserRating;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import com.azu.hospital.security.newsecurity.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "base_user_id_seq",
            sequenceName = "base_user_id_seq"
    )
    protected Long id;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "UserName Require")
    @NotNull(message = "UserName Require")
    @NotEmpty(message = "UserName Require")
    @Size(min = 5, message = "UserName too short")
    @Size(max = 80, message = "UserName too long")
    protected String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email Require")
    @Size(max = 50, message = "Email too long")
    @Email(
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Email Not Valid"
    )
    protected String email;
    @Column(nullable = false)
    @NotBlank(message = "Password Require")
    @NotBlank(message = "Password Require")
    @NotEmpty(message = "Password Require")
    @Size(max = 120, message = "Password  too long")
    @Size(min = 8, message = "Password Too short")
    protected String password;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Token> tokens;


    @ManyToMany(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    protected Set<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("createdAt desc")
    private List<UserRating> rate;
    private Boolean enabled = true;

    private Boolean accountNonExpired = true;

    private Boolean credentialsNonExpired = true;

    private Boolean accountNonLocked = true;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OtherItemAskingRequest> askingRequests;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manager")
    private Department departmentManger;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "departmentMangerAssistance")
    private Department departmentMangerAssistance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SoldBill> soldBill;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "baseUser")
    private List<UserShiftsTable> userShiftsTable;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "baseUser")
    private List<CustomSalary> customSalaries;

    @Column(updatable = false)
    private Instant createdAt;
    @Column(insertable = false)
    private Instant updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    private List<MainSalaryTable> mainSalaryTables;

    public BaseUser() {
    }

    public BaseUser(Long id, String username, String email, String password, Set<Role> roles, Boolean enabled,
                    Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled = true;
        this.accountNonExpired = accountNonExpired = true;
        this.credentialsNonExpired = credentialsNonExpired = true;
        this.accountNonLocked = accountNonLocked = true;

    }

    public BaseUser(String username, String email, String password, Set<Role> roles, Boolean enabled, Boolean accountNonExpired,
                    Boolean credentialsNonExpired, Boolean accountNonLocked
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled = true;
        this.accountNonExpired = accountNonExpired = true;
        this.credentialsNonExpired = credentialsNonExpired = true;
        this.accountNonLocked = accountNonLocked = true;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());

    }

    public List<UserShiftsTable> getUserShiftsTable() {
        return userShiftsTable;
    }

    public void setUserShiftsTable(List<UserShiftsTable> userShiftsTable) {
        this.userShiftsTable = userShiftsTable;
    }

    public List<CustomSalary> getCustomSalaries() {
        return customSalaries;
    }

    public void setCustomSalaries(List<CustomSalary> customSalaries) {
        this.customSalaries = customSalaries;
    }

    public List<MainSalaryTable> getMainSalaryTables() {
        return mainSalaryTables;
    }

    public void setMainSalaryTables(List<MainSalaryTable> mainSalaryTables) {
        this.mainSalaryTables = mainSalaryTables;
    }

    public List<OtherItemAskingRequest> getAskingRequests() {
        return askingRequests;
    }

    public void setAskingRequests(List<OtherItemAskingRequest> askingRequests) {
        this.askingRequests = askingRequests;
    }


    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return email;
    }


    public String getUsernameSpecial() {
        return username;
    }

    public Integer getLastRate() {
        return rate
                .stream()
                .map(UserRating::getRating)
                .findFirst()
                .orElse(0);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<String> getRolesSpecial() {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    public Department getDepartmentManger() {
        return departmentManger;
    }

    public void setDepartmentManger(Department departmentManger) {
        this.departmentManger = departmentManger;
    }

    public Department getDepartmentMangerAssistance() {
        return departmentMangerAssistance;
    }

    public void setDepartmentMangerAssistance(Department departmentMangerAssistance) {
        this.departmentMangerAssistance = departmentMangerAssistance;
    }


    public List<SoldBill> getSoldBill() {
        return soldBill;
    }

    public void setSoldBill(List<SoldBill> soldBill) {
        this.soldBill = soldBill;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
