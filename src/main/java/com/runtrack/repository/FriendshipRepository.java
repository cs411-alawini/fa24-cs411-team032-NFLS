import com.runtrack.entity.Friendship;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, UUID> {

    @Query("SELECT * FROM MakeFriends WHERE UserId = :userId")
    List<Friendship> findByUserId(UUID userId);

    @Query("SELECT * FROM MakeFriends WHERE FriendUserId = :friendUserId")
    List<Friendship> findByFriendUserId(UUID friendUserId);

    @Query("SELECT * FROM MakeFriends WHERE UserId = :userId OR FriendUserId = :userId")
    List<Friendship> findAllFriendshipsByUserId(UUID userId);
}
