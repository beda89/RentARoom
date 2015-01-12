package rentaroom.dtos;

/**
 * Created by Peter on 11.01.2015.
 */
public class DayDto {
    private Boolean isReserved=false;
    private Boolean isWeekend=false;
    private String selectBoxId;

    public Boolean getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public Boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }

    public String getSelectBoxId() {
        return selectBoxId;
    }

    public void setSelectBoxId(String selectBoxId) {
        this.selectBoxId = selectBoxId;
    }


}
