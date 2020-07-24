import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class PellGrant
{
    //Database Connection
    private static Connection c;
    private static String URL = "jdbc:sqlite:colleges.db";
    private static GrantDatabase grantDatabase;
    //text file comp
    private ArrayList<College> colArrayList = new ArrayList<College>();
    File file = new File("Pell-Answers.txt");
    BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
    // Database Component
    @BeforeClass
    public static void connectDatabase() throws Exception
    {
        c = DriverManager.getConnection(URL);
    }
    //Sets up database
    @Before
    public void setUp()
    {
        grantDatabase = new GrantDatabase();
    }

    public PellGrant() throws IOException {  }

    //Downloads Tsv from the link provided by Dr.Im
    @Test
    public void downloadTSV() throws MalformedURLException
    {
        java.net.URL urlObject = new URL("https://gist.githubusercontent.com/tacksoo/b32d630c2e5c7dcd8ebeb2fc67e9c7ae/raw/72b6bae956dfb3eeb66fa277f63ce8acb784fd01/pell.tsv");
        try {
            //Takes info from a source that isn't static, I.E. the Web/ Another computer
            //wrapper class
            //I had help from a tutor on this particular part
            InputStream bi = new BufferedInputStream((urlObject).openStream());
            FileOutputStream fo = new FileOutputStream("PellGrant.tsv");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = bi.read(buffer,0,1024)) != -1)
            {
                fo.write(buffer,0,bytesRead);
            }
        }
        catch(MalformedURLException m){System.out.println("Invalid Url Exception");} catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //
    @Test
    public void readingTSVbyTabs()
    {

    // Creates the scanner to read line by line

        Scanner input = new Scanner(System.in);
        File grantTsv = new File("PellGrant.tsv");
        try {
            input = new Scanner(grantTsv);

            // Skips over first couple of lines

            if (grantTsv.exists())
            {
                for (int i = 0; i < 5; i++)
                {
                    input.nextLine();
                }
            }

            // Reads the file line by line starting with the entry number of the college

            while (input.hasNext())
            {
                String scanString = input.nextLine();

                //Reads line until it hits a tab
                String[] splitString = scanString.split("\t");
                if(splitString[0].equals(""))
                {
                    break;
                }
                //Removes special characters from grant amount so string can be parsed into long
                String grantAmount = splitString[5].replaceAll("[^a-zA-Z0-9]", "");
                long grantMoney = Long.parseLong(grantAmount);

                //Parses Student Total into Integer
                String studentTotal = splitString[6].replaceAll("[^a-zA-Z0-9]", "");
                int stuTotal = Integer.parseInt(studentTotal);

                //Average amount is calculated here
                Double avgAmount = (double) (grantMoney / stuTotal);

                //Creates College Object using tab-separated file
                College objectCol = new College(Long.parseLong(splitString[0].trim()), splitString[1].trim(), splitString[2].trim(), splitString[3].trim(), splitString[4].trim(), grantMoney, stuTotal, avgAmount);
                colArrayList.add(objectCol);
         //       System.out.println(objectCol.printString());
            }

            //Other methods are also called here after all the information is collected as objects and put into an Arraylist
            calc10LowestAwards();
            calc10HighestAwards();
            calcMostRecipients();
            sortByState();
            bw.close();
        }
        catch(Exception e){e.printStackTrace();}
        input.close();
    }

    public ArrayList<College> getColArrayList()
    {
        return colArrayList;
    }

    public void setColArrayList(ArrayList<College> colArrayList)
    {
        this.colArrayList = colArrayList;
    }

    //Cycles and prints all values in the college list
    @Test
    public void printColArrayList()
    {
        for(College c : colArrayList)
        {
           System.out.println(c.printString());
        }
    }
@Test
    public void calc10LowestAwards() throws IOException
{
        System.out.println();
        //Sorts based on GrantPrices
        Collections.sort(colArrayList);
        //should print based on the grant prices
        for(College c : colArrayList)
        {
            //testing to make sure the sort is successful
         //   System.out.println(c.printString());
        }
        Date date = new Date();
        System.out.println("Calculated at " + date + "\n");
        System.out.println("Lowest Grant Price" + "\n");
        bw.newLine();
        bw.write(" Calculated at " + date);
        bw.newLine();
        bw.newLine();
        bw.write("Lowest Grant Price");
        bw.newLine();
        bw.newLine();

        for(int i = 0; i < 10;i++)
        {
            System.out.println((i + 1) + ") is : " + colArrayList.get(i).getName() + " : at : $" + colArrayList.get(i).getTotalGrant());
            bw.write((i + 1) + ") is : " + colArrayList.get(i).getName() + " : at : $" + colArrayList.get(i).getTotalGrant());
            bw.newLine();
        }
    }
    @Test
    public void calc10HighestAwards() throws IOException
    {
        System.out.println();
        //Sorts based on GrantPrices
        Collections.sort(colArrayList);
        //should print based on the grant prices
        for(College c : colArrayList)
        {
            //testing to make sure the sort is successful
              // System.out.println(c.printString());
        }
        int j = 1;
        System.out.println("Highest Grant Price" + "\n");
        bw.newLine();
        bw.write("Highest grant price ");
        bw.newLine();
        bw.newLine();
        for(int i = colArrayList.size()-1; i > colArrayList.size()-11;i--)
        {
            System.out.println(j + ") is : " + colArrayList.get(i).getName() + " : at : $" + colArrayList.get(i).getTotalGrant());
            bw.write(j + ") is : " + colArrayList.get(i).getName() + " : at : $" + colArrayList.get(i).getTotalGrant());
            bw.newLine();
            j++;
        }
        bw.newLine();
    }
    //calcs the College with the 10 most students
    public void calcMostRecipients() throws IOException
    {
        System.out.println();
        MostCollegeRecipients mostRecipients = new MostCollegeRecipients();
        Collections.sort(colArrayList,mostRecipients);
        int j = 1;
        System.out.println("Most Student recipients" + "\n");
        bw.write("Most Student recipients");
        bw.newLine();
        bw.newLine();
        for(int i = colArrayList.size()-1; i > colArrayList.size()-11;i--)
        {
            System.out.println(j + ") is : " + colArrayList.get(i).getName() + " : at : " + colArrayList.get(i).getNumStudents());
            bw.write(j + ") is : " + colArrayList.get(i).getName() + " : at : " + colArrayList.get(i).getNumStudents());
            bw.newLine();

            j++;
        }
    }

    public void sortByState()
    {
        System.out.println();
        SortByState stateSort = new SortByState();
        //comparator
        Collections.sort(colArrayList,stateSort);
        for(College c : colArrayList)
        {
            System.out.println(c.getName());
        }
    }

    // Database Connection Disconnects after class completes
    @AfterClass
    public static void disconnectDatabase() throws Exception
    {
        if (c != null)
        {
            c.close();
        }
    }
}
