package com.example.ejercicio3.core;

public class Ayuntamiento {

    private String adminCode2;
    private String adminCode1;
    private String adminName3;
    private String adminName2;
    private String lng;
    private String countryCode;
    private String postalCode;
    private String adminName1;
    private String ISO3166_2;
    private String placeName;
    private String lat;

    private static final String NotAplicable = "N/A";

    public Ayuntamiento( String adminCode2, String adminCode1,String adminName3, String adminName2, String lng, String countryCode, String postalCode, String adminName1, String ISO3166_2, String placeName, String lat){
        if (adminCode2==null || adminCode2.isEmpty()){
            this.adminCode2=NotAplicable;
        }
        if (adminCode1==null || adminCode1.isEmpty()){
            this.adminCode1=NotAplicable;
        }
        if (adminName2==null || adminName2.isEmpty()){
            this.adminName2=NotAplicable;
        }
        if (lng==null || lng.isEmpty()){
            this.lng=NotAplicable;
        }
        if (countryCode==null || countryCode.isEmpty()){
            this.countryCode=NotAplicable;
        }
        if (postalCode==null || postalCode.isEmpty()){
            this.postalCode=NotAplicable;
        }
        if (adminName1==null || adminName1.isEmpty()){
            this.adminName1=NotAplicable;
        }
        if (ISO3166_2==null || ISO3166_2.isEmpty()){
            this.ISO3166_2=NotAplicable;
        }
        if (placeName==null || placeName.isEmpty()){
            this.placeName=NotAplicable;
        }
        if (lat==null || lat.isEmpty()){
            this.lat=NotAplicable;
        }
        if (adminName3==null || adminName3.isEmpty()){
            this.adminName3=NotAplicable;
        }

        this.adminCode2=adminCode2.trim();
        this.adminCode1=adminCode1.trim();
        this.adminName2=adminName2.trim();
        this.lng=lng.trim();
        this.countryCode=countryCode.trim();
        this.postalCode=postalCode.trim();
        this.adminName1=adminName1.trim();
        this.ISO3166_2=ISO3166_2.trim();
        this.placeName=placeName.trim();
        this.lat=lat.trim();
        this.adminName3=adminName3.trim();

    }

    public String getAdminCode2() {
        return adminCode2;
    }

    public String getAdminCode1() {
        return adminCode1;
    }

    public String getAdminName2() {
        return adminName2;
    }

    public String getLng() {
        return lng;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public String getISO3166_2() {
        return ISO3166_2;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getLat() {
        return lat;
    }

    public static String getNotAplicable() {
        return NotAplicable;
    }

    @Override
    public String toString() {
        return adminName3 + ", " + adminName2 + ", " + adminName1 + ", " + placeName;
    }
}
