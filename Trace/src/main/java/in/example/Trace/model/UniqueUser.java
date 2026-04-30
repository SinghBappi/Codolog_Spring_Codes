package in.example.Trace.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "unique_users")
public class UniqueUser {
    @Id
    private UUID userId;
    private String deviceType;
    private String screenSize;
    private String browser;
    private LocalDateTime firstVisitTimestamp;

    public LocalDateTime getFirstVisitTimestamp() {
        return firstVisitTimestamp;
    }

    public void setFirstVisitTimestamp(LocalDateTime firstVisitTimestamp) {
        this.firstVisitTimestamp = firstVisitTimestamp;
    }


    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }



    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
