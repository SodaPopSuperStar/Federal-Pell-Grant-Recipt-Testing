import java.util.Comparator;

public class College implements Comparable<College>
{
    private long entryNum;
    private String name;
    private String city;
    private String state;
    private String type;
    private long totalGrant;
    private long numStudents;
    private double averageAmount;

    public College(long entryNum, String name, String city, String state, String type, long totalGrant, int numStudents, double averageAmount)
    {
        this.entryNum = entryNum;
        this.name = name;
        this.city = city;
        this.state = state;
        this.type = type;
        this.totalGrant = totalGrant;
        this.numStudents = numStudents;
        this.averageAmount = averageAmount;
    }

    public long getEntryNum()
    {
        return entryNum;
    }

    public void setEntryNum(long entryNum)
    {
        this.entryNum = entryNum;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public long getTotalGrant()
    {
        return totalGrant;
    }

    public void setTotalGrant(long totalGrant)
    {
        this.totalGrant = totalGrant;
    }

    public long getNumStudents()
    {
        return numStudents;
    }

    public void setNumStudents(int numStudents)
    {
        this.numStudents = numStudents;
    }

    public double getAverageAmount()
    {
        return averageAmount;
    }

    public void setAverageAmount(double averageAmount)
    {
        this.averageAmount = averageAmount;
    }

    public String printString()
    {
        String message = (entryNum + ", " + name + ", " + city + ", " + state + ", " + type + ", " + totalGrant + ", " + numStudents + ", " + averageAmount);
        return message;
    }
    //Compares the TotalGrants
    public int compareTo(College o)
    {
        return (int)(getTotalGrant() - o.getTotalGrant());
    }
}
