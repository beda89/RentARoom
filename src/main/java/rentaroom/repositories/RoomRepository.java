package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Room;

import java.util.List;

/**
 * Created by Peter on 28.11.2014.
 */

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, String> {

    public Room findOneByRoomNbr(String roomNbr);
}
