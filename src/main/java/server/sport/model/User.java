package server.sport.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String phone;
    private Collection<Activity> createdActivities;
    private Collection<ActivityStatus> activityStatuses;
    private Collection<Match> bestPlayedMatches;
    private Collection<UserResponsibility> userResponsibilities;
    private UserType userType;
    private Team team;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 6)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(age, user.age)) return false;
        if (!Objects.equals(gender, user.gender)) return false;
        if (!Objects.equals(phone, user.phone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @JsonBackReference(value="usersCreatedActivities")
    @OneToMany(mappedBy = "creator")
    public Collection<Activity> getCreatedActivities() {
        return createdActivities;
    }

    public void setCreatedActivities(Collection<Activity> createdActivities) {
        this.createdActivities = createdActivities;
    }

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "usersActivityStatuses")
    public Collection<ActivityStatus> getActivityStatuses() {
        return activityStatuses;
    }

    public void setActivityStatuses(Collection<ActivityStatus> activityStatuses) {
        this.activityStatuses = activityStatuses;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "playerOfTheMatch")
    @JsonBackReference( value = "bestPlayedMatches")
    public Collection<Match> getBestPlayedMatches() {
        return bestPlayedMatches;
    }

    public void setBestPlayedMatches(Collection<Match> bestPlayedMatches) {
        this.bestPlayedMatches = bestPlayedMatches;
    }

    @JsonBackReference(value = "userResponsibilities")
    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "userResponsibilities")
    public Collection<UserResponsibility> getUserResponsibilities() {
        return userResponsibilities;
    }

    public void setUserResponsibilities(Collection<UserResponsibility> userResponsibilities) {
        this.userResponsibilities = userResponsibilities;
    }

    @JsonBackReference(value = "userType")
    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id", nullable = false)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @JsonBackReference(value = "teamMembers")
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = true)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(String firstName, String lastName, String email, Integer age, String gender, String phone, Collection<Activity> createdActivities, Collection<ActivityStatus> activityStatuses, Collection<Match> bestPlayedMatches, Collection<UserResponsibility> userResponsibilities, UserType userType, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.createdActivities = createdActivities;
        this.activityStatuses = activityStatuses;
        this.bestPlayedMatches = bestPlayedMatches;
        this.userResponsibilities = userResponsibilities;
        this.userType = userType;
        this.team = team;
    }

    public User() {
    }

    public User(int userId, String firstName, String lastName, String email, Integer age, String gender, String phone, Collection<Activity> createdActivities, Collection<ActivityStatus> activityStatuses, Collection<Match> bestPlayedMatches, Collection<UserResponsibility> userResponsibilities, UserType userType, Team team) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.createdActivities = createdActivities;
        this.activityStatuses = activityStatuses;
        this.bestPlayedMatches = bestPlayedMatches;
        this.userResponsibilities = userResponsibilities;
        this.userType = userType;
        this.team = team;
    }

   /* @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                team.getTeamId() +
                '}';
    }
    /*
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", createdActivities=" + createdActivities +
                ", activityStatuses=" + activityStatuses +
                ", bestPlayedMatches=" + bestPlayedMatches +
                ", userResponsibilities=" + userResponsibilities +
                ", userType=" + userType +
                ", team=" + team +
                '}';
    }*/
}
