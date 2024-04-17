package soSchedule;

import java.util.Comparator;
import so.SOProcess;

public class FCFS extends SchedulerQueue {

    public FCFS() {
        super(new Comparator<SOProcess>() {
            @Override
            public int compare(SOProcess sp1, SOProcess sp2) {
                return -1;
            }
        });
    }
}
