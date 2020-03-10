import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Polynomial
{
  Polynomial(String input)
  {
    if (in_test.matches(input)==True)
    {
       input.replace("-","+-");
       for (for String polystring : input.replace("-","+-").split("+"))
       {
           String scalar_s,pow_s;
           int power;
           Scalar coefficient;
           //shit gets complicated because technically 7,7x,x,7x^2, and x^2
           //are all mathematically valid polyterms
           if !(input.contains("x"))
           {
               Polyterms.add//
           }
       }  
    }
  }
  
  public Polynomial add(Polyterm )

  private SortedSet<Polyterm> Polyterms=new TreeSet<>(
      Comparator.comparing(Polyterm::getExponent));

  // below can be used to test input
  private Pattern in_test = Pattern.compile("([+|-]?([(\d+)|\d*(\.?=\d+)?])?(x(\^\d+)?)?)*");
}
