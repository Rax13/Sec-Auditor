public class Timer {
    public static long startTime,endTime;
    public static void begin_timer(){
        startTime =  System.currentTimeMillis();
    }

    public static long get_timer(){
        endTime =  System.currentTimeMillis();
        long usedTime = endTime-startTime;
        //System.out.println(usedTime);
        return usedTime;
    }
}
