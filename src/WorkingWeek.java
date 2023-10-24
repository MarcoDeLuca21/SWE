import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class WorkingWeek {
    private Map<DayOfWeek, DailySchedule> schedule;

    public WorkingWeek(){
        schedule = new HashMap<>();
    }

    public void setDailySchedule(DayOfWeek day,DailySchedule dailySchedule){
        schedule.put(day, dailySchedule);
    }

    public DailySchedule getDailySchedule(DayOfWeek day){
        return schedule.get(day);
    }
}
