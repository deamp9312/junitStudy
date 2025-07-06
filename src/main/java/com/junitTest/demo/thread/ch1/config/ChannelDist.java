package com.junitTest.demo.thread.ch1.config;

import java.util.Collections;
import java.util.Set;

public class ChannelDist {
    public static volatile Set<String> notice = Set.of();
    public static void refreshNotice(Set<String> newData) {
        if (newData == null || newData.isEmpty()) {
            notice = Set.of(); // 비어 있는 불변 Set

        } else {
//            notice = newData;
            notice = Collections.unmodifiableSet(newData);
        }

        System.out.println("notice.list = " + notice);

    }

    public static boolean isNotice(String val) {
        return notice.contains(val); // lock-free 읽기
    }
}
