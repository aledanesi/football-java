package com.progetto.util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date Utility Class
 * This is used to convert Strings to Dates and Timestamps
 */
public class DateUtil 
{
    //~ Static fields/initializers =============================================

    private static String datePattern = "dd/MM/yyyy";
    private static String timePattern = "HH:mm:ss";
    private static String dateTimePattern1 = datePattern + " " + timePattern;
    private static String dateTimePattern2 = "ddMMyyyy HHmmss";
    private static String dateTimePattern3 = "dd/MM/yyyy HHmmss";
    private static TimestampConverter timestampConverter = new TimestampConverter();
    private static Log log = LogFactory.getLog(DateUtil.class);


    //~ Methods ================================================================

    /**
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() 
    {
        return datePattern;
    }

	/**
	 * @return a string representing the time pattern on the UI
	 */
	public static String getTimePattern() 
	{
		return timePattern;
	}

	/**
	 * @return a string representing the datetime pattern on the UI
	 */
	public static String getDateTimePattern1() 
	{
		return dateTimePattern1;
	}

    /**
     * @return a string representing the alternate datetime pattern on the UI
     */
    public static String getDateTimePattern2() 
    {
        return dateTimePattern2;
    }

    /**
     * @return a string representing the alternate datetime pattern on the UI
     */
    public static String getDateTimePattern3() 
    {
        return dateTimePattern3;
    }

    /**
     * This method attempts to convert a date
     * in the form dd-MMM-yyyy to dd/MM/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) 
    {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) 
        {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method returns the current time
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTime(Date theTime) 
    {
        return getDateTime(timePattern, theTime);
    }

	/**
	 * This method returns the full date and time
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getDateTime(Date theTime) 
	{
		return getDateTime(datePattern + " " + timePattern, theTime);
	}

    /**
     * This method returns the current date
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException 
    {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) 
    {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) 
        {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the 'datePattern' + 'timePattern'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the timestamp
     */
    public static final String convertTimestampToString(Timestamp aTimestamp) 
    {
        return getDateTime(datePattern+" "+timePattern, aTimestamp);
    }

    /**
     * This method generates a string representation of a date based
     * on the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the timestamp
     */
    public static final String convertTimestampToString(String aMask, Timestamp aTimestamp) 
    {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aTimestamp == null) 
        {
            log.error("aTimestamp is null!");
        } 
        else 
        {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aTimestamp);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate, String format) 
    {
        return getDateTime(format, aDate);
    }

    /**
     * This method generates a string representation of a timestamp based
     * on the System Property 'dateFormat'+''
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) 
    {
        return getDateTime(datePattern, aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate the date to convert (in format dd/MM/yyyy)
     * @return a date object
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) throws ParseException 
    {
        if (strDate==null) return null;
        
        Date aDate = null;

        try 
        {
            if (log.isDebugEnabled()) 
            {
                log.debug("converting date with pattern: " + datePattern);
            }

            aDate = convertStringToDate(datePattern, strDate);
        } 
        catch (ParseException pe) 
        {
            log.error("Could not convert '" + strDate + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
                    
        }

        return aDate;
    }

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String aMask, String strDate) throws ParseException 
	{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) 
		{
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try 
		{
			date = df.parse(strDate);
		} 
		catch (ParseException pe) 
		{
			//log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static Timestamp addEndOfDay(Timestamp tms)
	{
		String tmsDateString = DateUtil.getDate(tms);
		tmsDateString += " 23:59:59";
		return (Timestamp)timestampConverter.convert(Timestamp.class, tmsDateString);
	}
	
	public static String getFormatData(long millis)
	{
		long seconds = millis / 1000;
		long minutes = 0;
		long hours = 0;
		
		if (seconds >= 60){
			minutes = seconds / 60;
		}
		if (minutes >= 60){
			hours = minutes / 60;
			minutes = minutes % 60;
		}
		DecimalFormat format = new DecimalFormat();
		format.setMinimumIntegerDigits(2);
		String strHours = format.format(hours);
		String strMinutes = format.format(minutes);
		String result = strHours + ":" + strMinutes;
		return result;
	}

    //Alternative formatting of a time interval in milliseconds
    public static String getFormatData2(long millis)
    {
        long seconds = Math.abs(millis) / 1000;
        long minutes = 0;
        long hours = 0;
        
        if (seconds >= 60){
            minutes = seconds / 60;
            seconds = seconds % 60;
        }
        if (minutes >= 60){
            hours = minutes / 60;
            minutes = minutes % 60;
        }
        String result = (millis > 0)?"":"-";
        if (hours>0)
        {
            if (hours<10)
              result += "0";
            result += hours+"h";
        }
        
        if (hours>0 || minutes>0)
        {
            if (minutes<10)
              result += "0";
            result += minutes+"m";
        }
        
        if (seconds<10)
          result += "0";
        result += seconds+"s";

        return result;
    }
    
    /*
     * Method for converting a Double to a Date using format "yyyyMMdd"
     * (Used mainly for getting dates from AS400)
     */
    public static Date convertLongToDate(String l) throws SQLException
    {
        if (l == null) return null;
        l = l.trim();
        if ("0".equals(l)) return null;
        try
        {
            return DateUtil.convertStringToDate("yyyyMMdd", l);
        }
        catch(ParseException pe)
        {
            throw new SQLException("Invalid date format. Unable to parse string '"+l+"' into format yyyyMMdd.");
        }
    }

    /*
     * Method for converting a Double to a Date using the specified format
     * (Used mainly for getting dates from AS400)
     */
    public static Date convertLongToDate(String l, String format) throws SQLException
    {
        if (l == null) return null;
        l = l.trim();
        if ("0".equals(l)) return null;
        //make sure the date is 8 char long, otherwise add a zero up front
        if(l.length()<8)
        {
            l = "0"+l;
        }
        try
        {
            return DateUtil.convertStringToDate(format, l);
        }
        catch(ParseException pe)
        {
            throw new SQLException("Invalid date format. Unable to parse string '"+l+"' into format '"+format+"'");
        }
    }

	public static double getFormatDataInHours(double millis)
	{
		return millis / 1000 / 60 / 60;
	}
	
	public static void main(String[] args) {
		System.out.println(getFormatData2(6677001));
	}
	
}
