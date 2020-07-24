import java.io.IOException;

public class Main
{
  public static void main(String[] args)
  {
      try
      {
        PellGrant tester = new PellGrant();
        //
        tester.downloadTSV();
        // readingTSVbyTabs is effectively the on button
        tester.readingTSVbyTabs();
      }catch(IOException e)
      {
          e.printStackTrace();
      }
  }
}
