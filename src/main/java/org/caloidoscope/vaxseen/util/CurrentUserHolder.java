package org.caloidoscope.vaxseen.util;

import org.caloidoscope.vaxseen.entity.User;

public class CurrentUserHolder {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void set(User user) {
        currentUser.set(user);
    }

    public static User get() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
