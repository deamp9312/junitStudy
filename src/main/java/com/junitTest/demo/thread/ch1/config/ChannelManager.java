package com.junitTest.demo.thread.ch1.config;

import java.util.*;

public class ChannelManager {
    public static Set<String> selectChannelDist(){
        HashSet<String> strings = new HashSet<>();
        strings.add("N00001");
        strings.add("N00002");
        strings.add("N00003");
        strings.add("N00004");
        return strings;
    }
}
