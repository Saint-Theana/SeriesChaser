//
// Decompiled by Jadx - 872ms
//
package com.robot;

import java.util.HashMap;
import java.util.Iterator;

public class CookieStore {
    private HashMap<String, String> a = new HashMap<>();

    public CookieStore() {
    }

    public boolean a(String str) {
        if (!str.contains("=")) {
            return false;
        }
        int indexOf = str.indexOf("=");
        String replaceAll = str.substring(0, indexOf).replaceAll(" ", "");
        String substring = str.contains(";") ? str.substring(indexOf + 1, str.indexOf(";")) : str.substring(indexOf + 1);
        if (substring.equals("")) {
            return false;
        }
        this.a.put(replaceAll, substring);
        return true;
    }

    public String toString() {
        String str = "";
        Iterator<String> it = this.a.keySet().iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2;
            }
            String next = it.next();
            str = str2 + next + "=" + this.a.get(next) + ";";
        }
    }
}

