package com.kampen.riksSe.leader.activity.fragments.map.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class MapConstants {


    public static final ArrayList<String> HIGHWAYS = new ArrayList<>();

    public static  void init()
    {
        HIGHWAYS.add("Route 9");
        HIGHWAYS.add("Route 11");
        HIGHWAYS.add("Route 13");
        HIGHWAYS.add("Route 15");
        HIGHWAYS.add("Route 17");
        HIGHWAYS.add("Route 19");
        HIGHWAYS.add("Route 21");
        HIGHWAYS.add("Route 23");
        HIGHWAYS.add("Route 24");
        HIGHWAYS.add("Route 25");
        HIGHWAYS.add("Route 26");
        HIGHWAYS.add("Route 27");
        HIGHWAYS.add("Route 28");
        HIGHWAYS.add("Route 29");
        HIGHWAYS.add("Route 30");
        HIGHWAYS.add("Route 31");
        HIGHWAYS.add("Route 32");
        HIGHWAYS.add("Route 34");
        HIGHWAYS.add("Route 35");
        HIGHWAYS.add("Route 37");
        HIGHWAYS.add("Route 40");
        HIGHWAYS.add("Route 41");
        HIGHWAYS.add("Route 42");
        HIGHWAYS.add("Route 44");
        HIGHWAYS.add("Route 46");
        HIGHWAYS.add("Route 47");
        HIGHWAYS.add("Route 49");
        HIGHWAYS.add("Route 50");
        HIGHWAYS.add("Route 51");
        HIGHWAYS.add("Route 52");
        HIGHWAYS.add("Route 53");
        HIGHWAYS.add("Route 55");
        HIGHWAYS.add("Route 56");
    }


    public static boolean   isRouteOnHighWay(String htmlHiperlink)
    {
        for (int i=0; i<HIGHWAYS.size(); i++){
            if(htmlHiperlink.contains(HIGHWAYS.get(i))){

                return  true;

            }
        }


        return  false;
    }
}
