package com.rakesh.nytimessearch.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rparuthi on 3/15/2017.
 */

public class Headline {
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("print_headline")
    @Expose
    private String printHeadline;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }
}
