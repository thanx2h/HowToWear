package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("pod")
    String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "pod='" + pod + '\'' +
                '}';
    }
}
