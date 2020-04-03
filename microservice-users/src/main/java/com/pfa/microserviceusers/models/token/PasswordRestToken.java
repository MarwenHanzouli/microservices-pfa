package com.pfa.microserviceusers.models.token;

import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.audit.AbstractEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class PasswordRestToken extends AbstractEntity {

    private static final int EXPIRATION = 60 * 25;

    @Column(name="reset_token")
    private String resetToken;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private Date expirationDate;

    public PasswordRestToken() {
    }

    public PasswordRestToken(User user) {
        this.user = user;
        this.expirationDate=new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        this.resetToken= UUID.randomUUID().toString();
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
