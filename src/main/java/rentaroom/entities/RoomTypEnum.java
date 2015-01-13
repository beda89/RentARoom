package rentaroom.entities;

/**
 * Created by Peter on 13.01.2015.
 */
public enum RoomTypEnum {

    SINGLE_ROOM, SINGLE_ROOM_ONE_CHILD, SINGLE_ROOM_TWO_CHILDREN, DOUBLE_ROOM, DOUBLE_ROOM_ONE_CHILD, THREE_PERSONS;

    private static RoomTypEnum[] allValues = values();
    public static RoomTypEnum fromOrdinal(int n) {return allValues[n];}
}
