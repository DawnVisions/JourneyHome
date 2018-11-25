package database;

import com.dawnvisions.journeyhome.Dashboard.Task;

import java.util.ArrayList;

public class TaskSource
{

    public static ArrayList<Task> tasks;

    static
    {
        tasks = new ArrayList<>();
        tasks.add(0, new Task(0, "Learn about the NICU and my baby's plan of care", true, true));
        tasks.add(1, new Task(1, "Review parent binder with staff", false, true));
        tasks.add(2, new Task(2, "Meet my baby's providers", true, true));
        tasks.add(3, new Task(3, "Review criteria for discharge", true,true));
        tasks.add(4, new Task(4, "Get baby's car seat", true, true));
        tasks.add(5, new Task(5, "Add my baby to insurance plan", true, true));
        tasks.add(6, new Task(6, "Choose my baby's follow up doctor", true, true));
        tasks.add(7, new Task(7, "Talk to the nurse about caring for my baby", false, true));
        tasks.add(8, new Task(8, "Attend rounds when I can", false, true));
        tasks.add(9, new Task(9, "Weaning breathing support", false, true));
        tasks.add(10, new Task(10, "I am comfortable holding my baby skin-to-skin", true, true));
        tasks.add(11, new Task(11, "My baby is done with antibiotic treatment", false, true));
        tasks.add(12, new Task(12, "My baby is tolerating full volume feedings", true, true));
        tasks.add(13, new Task(13, "My baby can stay warm in a crib", false,true ));
        tasks.add(14, new Task(14, "Talk about long term respiratory needs", false, false));
        tasks.add(15, new Task(15, "My baby has weaned off the ventilator", false, false));
        tasks.add(16, new Task(16, "My baby is breathing well on room air", false, true));
        tasks.add(17, new Task(17, "I have met with Therapists and Consults", true, true));
        tasks.add(18, new Task(18, "I am comfortable feeding my baby", false, true));
        tasks.add(19, new Task(19, "My baby is taking 80% of their feedings by breast or bottle", false, true));
        tasks.add(20, new Task(20, "Talk about long-term feeding goals and make a feeding plan", false, false));
        tasks.add(21, new Task(21, "Car seat trial done", false, true));
        tasks.add(22, new Task(22, "My baby has no breathing or heart rate events", false,true));
        tasks.add(23, new Task(23, "My baby is gaining weight on discharge feeding plan", false,true));
        tasks.add(24, new Task(24, "I have completed parent discharge eduction", true, true));
        tasks.add(25, new Task(25, "I have made my baby's feeding recipe for home", false, false));
        tasks.add(26, new Task(26, "My baby has completed required testing", true, true));
        tasks.add(27, new Task(27, "I am able to provide all care for my baby (Room-in with baby for 24-72 hours if needed)", false, true));
        tasks.add(28, new Task(28, "I have picked up discharge medicines and practiced drawing them up with a nurse", false, true));
        tasks.add(29, new Task(29, "My baby's discharge exam is done. Discharge plan of care was given to me", false, true));
        tasks.add(30, new Task(30, "I reviewed discharge information and Parent Binder with my baby's nurse", false, true));
        tasks.add(31, new Task(31, "Pack my baby's belongings, including any frozen breast milk", true, true));

    }

    public static void RespiratoryDetour(boolean selected)
    {
        tasks.get(14).setActive(selected);
        tasks.get(15).setActive(selected);
        if(selected)
            tasks.get(16).setInstruction("My baby is breathing well on room air or has a home oxygen plan in place");
        else
            tasks.get(16).setInstruction("My baby is breathing well on room air");
    }

    public static void FeedingDetour(boolean selected)
    {
       tasks.get(20).setActive(selected);
       tasks.get(25).setActive(selected);
    }
}
