package com.jfootball.util;

public class SystemUtil
{
    //This method calculates the amount of used memory by the JVM
    public static String calculateMemoryUsage()
    {
        //First initiate a garbage collection
        System.gc();
        
        //Calculate memory usage
        long totalMem = Runtime.getRuntime().totalMemory();
        long usedMem = totalMem - Runtime.getRuntime().freeMemory();

        //Calculate how to display size
        int depth;
        for(depth = 0; usedMem / 1024L > 1L && totalMem > 9999L && depth < 4; depth++)
        {
            usedMem /= 1024L;
            totalMem /= 1024L;
        }

        String postSize;
        switch(depth)
        {
        case 0: // '\0'
            postSize = "B";
            break;

        case 1: // '\001'
            postSize = "KB";
            break;

        case 2: // '\002'
            postSize = "MB";
            break;

        case 3: // '\003'
            postSize = "GB";
            break;

        case 4: // '\004'
        default:
            postSize = "TB";
            break;
        }
        
        return "Memory usage: " + usedMem + postSize + " of " + totalMem + postSize;
    }

}
