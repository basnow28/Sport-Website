package server.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import server.sport.model.Team;
import server.sport.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.users u SET u.team_id = NULL WHERE u.user_id = ?1", nativeQuery = true)
    int removeFromTeam(int user_id);
}
