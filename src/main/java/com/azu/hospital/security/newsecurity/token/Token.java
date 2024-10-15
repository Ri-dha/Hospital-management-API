package com.azu.hospital.security.newsecurity.token;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.xmlbeans.XmlCursor;
import org.aspectj.weaver.patterns.BasicToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token{

    @Id
    @GeneratedValue
    private Long tokenId;

    @Column(columnDefinition = "TEXT")
    private String token;
//    private boolean isExpire = false;
//    private boolean isRevoke = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TokenTypeEnum tokenType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BasicTokenEnum basicTokenEnum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_user_id")
    private BaseUser users;

    public String getToken() {
        return token;
    };

    public void setToken(String token) {
        this.token = token;
    };


}