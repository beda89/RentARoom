package rentaroom.dtos;

import java.util.Date;

/**
 * Created by Peter on 12.01.2015.
 */
public class ReservationDates {

    private Date beginDate;
    private Date endDate;


    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
