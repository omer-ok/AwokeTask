
package com.kampen.riksSe.leader.activity.modelV3;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Specifications {

    @SerializedName("processor")
    private String mProcessor;
    @SerializedName("ram")
    private String mRam;
    @SerializedName("storage")
    private String mStorage;

    public String getProcessor() {
        return mProcessor;
    }

    public void setProcessor(String processor) {
        mProcessor = processor;
    }

    public String getRam() {
        return mRam;
    }

    public void setRam(String ram) {
        mRam = ram;
    }

    public String getStorage() {
        return mStorage;
    }

    public void setStorage(String storage) {
        mStorage = storage;
    }

}
