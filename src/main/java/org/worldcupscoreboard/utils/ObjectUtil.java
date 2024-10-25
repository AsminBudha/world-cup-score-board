package org.worldcupscoreboard.utils;

public class ObjectUtil {
    public  static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "Object must not be null");
    }
}
