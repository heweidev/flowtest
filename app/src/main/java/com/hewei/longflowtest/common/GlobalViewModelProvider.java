package com.hewei.longflowtest.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengyinpeng on 2018/9/7.
 */
public class GlobalViewModelProvider {
    private static Map<String, ViewModelProvider> sMap = new HashMap<>();

    public static ViewModelProvider getProvider(String key) {
        ViewModelProvider provider = sMap.get(key);
        if (provider == null) {
            provider = new ViewModelProvider(new ViewModelStore(), new ViewModelProvider.NewInstanceFactory());
            sMap.put(key, provider);
        }

        return provider;
    }

    public static <T  extends ViewModel> T get(String key, Class<T> cls) {
        return getProvider(key).get(cls);
    }

    /**
     * 清空某个Provider的所有ViewModel
     * @param key
     */
    public static void clear(String key) {
        ViewModelProvider provider = sMap.get(key);
        sMap.put(key, null);

        try {
            Field field = ViewModelProvider.class.getField("mViewModelStore");
            field.setAccessible(true);
            ViewModelStore store = (ViewModelStore) field.get(provider);
            store.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
