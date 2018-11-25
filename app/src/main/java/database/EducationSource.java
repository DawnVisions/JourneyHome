package database;

import java.util.ArrayList;

import model.EducationItem;

public class EducationSource
{
    public static ArrayList<EducationItem> eds;

    static
    {
        eds = new ArrayList<>();
        eds.add(new EducationItem("video", "Car Seat Safety", false));
        eds.add(new EducationItem("video", "Home Safety", false));
        eds.add(new EducationItem("video", "Period of Purple Crying", false));
        eds.add(new EducationItem("video", "Safe Sleep", false));
        eds.add(new EducationItem("video", "Infant CPR", false));
        eds.add(new EducationItem("skill", "Change a diaper", false));
        eds.add(new EducationItem("skill", "Check a temperature", false));
        eds.add(new EducationItem("skill", "Give a bath", false));
        eds.add(new EducationItem("skill", "Feed my baby", false));
        eds.add(new EducationItem("skill", "Make feeding recipe", false));
        eds.add(new EducationItem("skill", "Give my baby medicine", false));
    }

}
