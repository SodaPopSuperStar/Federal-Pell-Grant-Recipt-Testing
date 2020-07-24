import java.util.Comparator;

public class SortByState implements Comparator<College>
{
    public int compare(College o1, College o2)
    {
        int comparison = 0;
        comparison =  o1.getState().compareTo(o2.getState());
        if(comparison == 0)
        {
            return o1.getName().compareTo(o2.getName());
        }
        return comparison;
    }
}
