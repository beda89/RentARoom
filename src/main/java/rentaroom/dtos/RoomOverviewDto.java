package rentaroom.dtos;

import java.util.List;

/**
 * Created by Peter on 11.01.2015.
 */
public class RoomOverviewDto {

    private List<RoomDto> rooms;
    private List<DayHeaderDto> headerList;

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }

    public List<DayHeaderDto> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<DayHeaderDto> headerList) {
        this.headerList = headerList;
    }
}
