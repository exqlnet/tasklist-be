package com.ncuhome.tasklist.util;

import com.ncuhome.tasklist.dataobject.User;

public class Global {
    ThreadLocal<User> user = new ThreadLocal<>();
}
