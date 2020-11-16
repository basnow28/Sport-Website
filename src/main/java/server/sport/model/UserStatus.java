package server.sport.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user_statuses")
public class UserStatus {
    private int statusId;
    private String statusName;
    private Collection<ActivityStatus> activityStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    @Column(name = "status_id", nullable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "status_name", nullable = false, length = 45)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatus status = (UserStatus) o;

        if (statusId != status.statusId) return false;
        if (!Objects.equals(statusName, status.statusName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userStatus")
    @JsonBackReference
    public Collection<ActivityStatus> getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Collection<ActivityStatus> activityStatus) {
        this.activityStatus = activityStatus;
    }

    public UserStatus(String statusName, Collection<ActivityStatus> activityStatus) {
        this.statusName = statusName;
        this.activityStatus = activityStatus;
    }

    public UserStatus() {
    }

    //deal with Collection later
    /*
    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", activityStatus=" + activityStatus +
                '}';
    }*/
}
