package com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {
    public int id;
    public String status;
    public String timestamp;
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.status);
        dest.writeString(this.timestamp);
    }

    public Model() {

    }

    protected Model(Parcel in) {
        this.id = in.readInt();
        this.status = in.readString();
        this.timestamp = in.readString();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
