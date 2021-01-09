package android.os;

/**
 * Created by ksander on 15/02/18.
 */
public class Parcel {

    public void writeLong(long l){
    }

    public long readLong(){
        return 0;
    }

    public void writeByte(Byte b){
    }

    public byte readByte(){
        return 0;
    }

    public void writeInt(Integer i){
    }

    public int readInt(){
        return 0;
    }

    public void writeParcelable(Parcelable p, int flag) {
    }

    public <T extends Parcelable> T readParcelable(ClassLoader classLoader){
        return null;
    }

    public void writeString(String s){
    }

    public String readString(){
        return "";
    }

    public void writeFloat(Float f){
    }

    public float readFloat(){
        return 0;
    }

}
