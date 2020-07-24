import java.util.Comparator;

public class MostCollegeRecipients implements Comparator<College>
{
    public int compare(College o1, College o2)
    {
        if(o1.getNumStudents() > o2.getNumStudents())
        {
            return 1;
        }
        else if(o1.getNumStudents() < o2.getNumStudents())
        {
            return -1;
        }
        else
        return 0;
    }
}
