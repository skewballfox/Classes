import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Polynomial {
  Polynomial(string input){
   if (in_test.matches(input)==True)
       input.replace("-","+-");
       for string
  }
  private SortedSet<Polyterm> Polyterms=new TreeSet<>(
      Comparator.comparing(Polyterm::getExponent));

  // below can be used to test input
  private Pattern in_test = Pattern.compile("([+|-]?([(\d+)|\d*(\.?=\d+)?])?(x(\^\d+)?)?)*");
}
