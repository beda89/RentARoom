package rentaroom.Utils;

import rentaroom.dtos.ReservationDates;
import rentaroom.entities.Room;
import rentaroom.entities.RoomTypEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Peter on 08.01.2015.
 */
public class CommonUtils {


    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    public static long STORNO_GEBUEHR = 1000L;

    public static Date getDateWithoutTime(Date chosenDate) throws ParseException{
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date dateWithZeroTime =formatter.parse(formatter.format(chosenDate));

        return dateWithZeroTime;
    }

    public static String getFormattedDateString(Date date){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.");
        return dateFormatter.format(date);
    }

    public static String getFormDateString(Date date){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormatter.format(date);
    }

    public static Boolean checkIfReserved(ArrayList<ReservationDates> reservationDatesList,Date date){

        if(reservationDatesList==null){
            return false;
        }

        for(ReservationDates reservationDates:reservationDatesList){
            if(reservationDates.getBeginDate().getTime()<=date.getTime() && reservationDates.getEndDate().getTime()>=date.getTime()){
                return true;
            }
        }

        return false;
    }

    public static Date parseCheckboxDate(String checkBoxDate){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");


        try {
            return dateFormatter.parse(checkBoxDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Long getRoomPriceForSelectionAndDays(Room room, RoomTypEnum roomTyp, Long daysCount){

        if(roomTyp.equals(RoomTypEnum.DOUBLE_ROOM)){
            return room.getPrice_doubleRoom()*daysCount;
        }

        if(roomTyp.equals(RoomTypEnum.DOUBLE_ROOM_ONE_CHILD)){
            return room.getPrice_doubleRoomOneChild()*daysCount;
        }

        if(roomTyp.equals(RoomTypEnum.SINGLE_ROOM)){
            return room.getPrice_singleRoom()*daysCount;
        }

        if(roomTyp.equals(RoomTypEnum.SINGLE_ROOM_ONE_CHILD)){
            return room.getPrice_singleRoomOneChild()*daysCount;
        }

        if(roomTyp.equals(RoomTypEnum.SINGLE_ROOM_TWO_CHILDREN)){
            return room.getPrice_singleRoomTwoChildren()*daysCount;
        }

        if(roomTyp.equals(RoomTypEnum.THREE_PERSONS)){
            return room.getPrice_threePersons()*daysCount;
        }


        return 0L;
    }


    public static String getGermanWeekday(Date date){

        int day=date.getDay();

        if(day==0){
            return "Sonntag";
        }

        if(day==1){
            return "Montag";
        }

        if(day==2){
            return "Dienstag";
        }

        if(day==3){
            return "Mittwoch";
        }

        if(day==4){
            return "Donnerstag";
        }

        if(day==5){
            return "Freitag";
        }

        return "Samstag";
    }

}
