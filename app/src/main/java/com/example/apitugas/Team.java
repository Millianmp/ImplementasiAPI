package com.example.apitugas;

import com.google.gson.annotations.SerializedName;

public  class Team {

    @SerializedName("strTeam")
    public String strTeam;

    @SerializedName("strStadium")
    public String strStadium;

    @SerializedName("strBadge")
    public String strBadge;
}