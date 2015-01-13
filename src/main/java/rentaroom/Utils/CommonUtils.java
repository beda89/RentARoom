package rentaroom.Utils;

import rentaroom.dtos.ReservationDates;

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
