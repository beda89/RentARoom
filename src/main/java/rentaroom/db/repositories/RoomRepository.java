package rentaroom.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rentaroom.db.entities.Room;

/**
 * Created by Peter on 28.11.2014.
 */
public interface RoomRepository extends MongoRepository<Room,Long> {
}
