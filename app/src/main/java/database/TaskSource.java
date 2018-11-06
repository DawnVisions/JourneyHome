package database;

import com.dawnvisions.journeyhome.Task;

import java.util.ArrayList;

public class TaskSource
{

    public static ArrayList<Task> tasks;

    static
    {
        tasks = new ArrayList<Task>();
        tasks.add(new Task(0, "Learn about the NICU and my baby's plan of care", true));
        tasks.add(new Task(1, "Review parent binder with staff", false));
        tasks.add(new Task(2, "Meet my baby's providers", true));
        tasks.add(new Task(3, "Review criteria for discharge", true));
        tasks.add(new Task(4, "Get baby's car seat", true));
        tasks.add(new Task(5, "Add my baby to insurance plan", true));
        tasks.add(new Task(6, "Choose my baby's follow up doctor", true));
        tasks.add(new Task(7, "Talk to the nurse about caring for my baby", false));
        tasks.add(new Task(8, "Attend rounds when I can", false));
        tasks.add(new Task(9, "Weaning breathing support", false));
        tasks.add(new Task(10, "I am comfortable holding my baby skin-to-skin", true));
        tasks.add(new Task(11, "My baby is done with antibiotic treatment", false));
        tasks.add(new Task(12, "My baby is tolerating full volume feedings", true));
    }
}
