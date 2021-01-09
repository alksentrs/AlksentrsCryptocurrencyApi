package android.os;

/**
 * Created by ksander on 15/02/18.
 */
public interface Parcelable {

    void writeToParcel(Parcel dest, int flags);
    int describeContents();
}
