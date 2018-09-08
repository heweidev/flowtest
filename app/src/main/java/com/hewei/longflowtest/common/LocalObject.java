package com.hewei.longflowtest.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.LongSparseArray;

import java.lang.ref.WeakReference;

/**
 * 对于单进程应用，提供一个可以通过Intent和Bundle传递的对象
 */
public class LocalObject<T> implements Parcelable {
    private static final String TAG = "LocalObject";
    private static LongSparseArray<WeakReference<Object>> sData = new LongSparseArray<>(20);
    private int mIndex = -1;
    private T mObject;
    private static int sIndex = 0;

    public LocalObject(T obj) {
        mObject = obj;
    }

    protected LocalObject(Parcel in) {
        int index = in.readInt();
        if (index <= 0) {
            return;
        }

        WeakReference<Object> ref = sData.get(index);
        if (ref != null) {
            try {
                mObject = (T) ref.get();
                sData.removeAt(index);
            } catch (ClassCastException e) {
                Log.w(TAG, "class cast failed!");
            }
        }
    }

    public T get() {
        return mObject;
    }

    public static final Creator<LocalObject> CREATOR = new Creator<LocalObject>() {
        @Override
        public LocalObject createFromParcel(Parcel in) {
            return new LocalObject(in);
        }

        @Override
        public LocalObject[] newArray(int size) {
            return new LocalObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mObject != null) {
            if (mIndex <= 0) {
                mIndex = ++sIndex;
            }

            sData.put(mIndex, new WeakReference<Object>(mObject));
        }

        dest.writeLong(mIndex);
    }

    private static void gc() {
        int size = sData.size();
        for(int i = 0; i < size; i++) {
            WeakReference<Object> ref = sData.valueAt(i);
            if (ref != null && ref.get() == null) {
                sData.removeAt(i);
            }
        }
    }
}
