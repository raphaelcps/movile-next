package com.movile.next.seriestracker.model;

import java.io.Serializable;

/**
 * Created by Raphael on 03/07/2015.
 */
public class Airs implements Serializable {
    private String day;
    private String time;
    private String timezone;

    public String day() {
        return day;
    }

    public String time() {
        return time;
    }

    public String timezone() {
        return timezone;
    }

}
