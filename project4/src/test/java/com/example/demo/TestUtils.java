package com.example.demo;

import java.lang.reflect.Field;

public class TestUtils {

    // REFERENCE NOTE: From lesson *, video *
    public static void injectObjects(Object target, String field, Object injectible) {
        boolean isPrivate = false;
        try {
            Field declaredField = target.getClass().getDeclaredField(field);
            if(!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
                isPrivate = true;
            }
            declaredField.set(target, injectible);
            if(isPrivate) {
                declaredField.setAccessible(false);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
