package com.resuadam.androidtime.core;


/** Represents the date and time info retrieved from server. */
public class DateTime {
    public static DateTime INVALID = new DateTime( "", "", "", " ", "", "", "" );
    private static final String NotApplicable = "N/A";

    /** Creates a date and time object.
     * @param dateTime the date and time info, as a string.
     * @param timeInfo info related to the time, as a string.
     * @param gmtInfo the gmt zone info, as a string.
     */
    public DateTime(String dateTime, String timeInfo, String gmtInfo, String lat, String lng, String sunrise, String sunset)
    {
        if ( dateTime == null
                || dateTime.isEmpty() )
        {
            dateTime = NotApplicable;
        }

        if ( timeInfo == null
                || timeInfo.isEmpty() )
        {
            timeInfo = NotApplicable;
        }

        if ( gmtInfo == null
                || gmtInfo.isEmpty() )
        {
            gmtInfo = NotApplicable;
        }
        if (lat==null || lat.isEmpty()){
            lat=NotApplicable;
        }
        if (lng==null || lng.isEmpty()){
            lng=NotApplicable;
        }
        if (sunrise==null || sunrise.isEmpty()){
            sunrise=NotApplicable;
        }
        if (sunset==null||sunset.isEmpty()){
            sunset=NotApplicable;
        }

        this.dateTime = dateTime.trim();
        this.timeInfo = timeInfo.trim();
        this.gmtInfo = gmtInfo.trim();
        this.lat = lat.trim();
        this.lng=lng.trim();
        this.sunrise = sunrise.trim();
        this.sunset=sunset.trim();
    }

    /** @return the date and time info, as a string. */
    public String getDateTime()
    {
        return this.dateTime;
    }

    /** @return the time info, as a string. */
    public String getTimeInfo()
    {
        return this.timeInfo;
    }

    /** @return the gmt info, as a string. */
    public String getGmtInfo()
    {
        return this.gmtInfo;
    }

    public String getLat(){
        return this.lat;
    }
    public String getLng(){
        return lng;
    }
    public String getSunrise(){
        return sunrise;
    }
    public String getSunset(){
        return sunset;
    }

    private String dateTime;
    private String timeInfo;
    private String gmtInfo;
    private String lat;
    private String lng;
    private String sunrise;
    private String sunset;
}