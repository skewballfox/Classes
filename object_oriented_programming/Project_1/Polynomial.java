import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Polynomial
{ //Note I'm probably going to rewrite tomorrow after the test
  //using either a linked list or an arraylist. Sorted Set is
  //just not a good fit for this problem
  Polynomial(SortedSet polyterms)
  {
      Polyterms=polyterms;
  }  
  Polynomial(String input) throws ArithmeticException
  {
    if (in_test.matches(input)==True)
    {
       input.replace("-","+-");
       for (for String polystring : input.replace("-","+-").split("+"))
       {
           //Note, given that this is being supplied directly from the user
           //I think it would be okay to put a little faith in them and not
           //check that an item added has a duplicate exponent(x^2 and 4x^2)
           //I could simply copy paste some of the code from the add function,
           //however given that each call of the add is returning a new Polynomial
           //and I would have to do this for each item in the list, I don't think think
           //I should reuse the function unless I was modifying the set of Polyterms directly
           Polyterms.add(new Polynomial(polystring))
       }
             
    } else
    {
        throw new ArithmeticException("Not a valid Polynomial");
    }
  }
  public Polynomial add(Polynomial addend)
  { 
      sortedSet<Polyterm> tempPolyterms = Polyterms.clone();
      for (Polyterm item: addend.getPolyterms()){
          if (!Polyterms.contains(addend)){
              tempPolyterms.add(item);
          }else{
              //this should work because the comparator is set to be the exponent
              Polyterm temp=Polyterms.ceiling(item);
              tempPolyterms.remove(addend);
              tempPolyterms.add(temp.add(addend));
          }
      }
      return new Polynomial(tempPolyterms);
  }
  public Polynomial evaluate(Scalar multiplicand)
  {
      
      SortedSet<Polyterm> tempPolyterms=new TreeSet<>(
      Comparator.comparing(Polyterm::getExponent));
      for 
  }

  public SortedSet<Polyterm> getPolyterms(){
      return Polyterms;
  }
  private SortedSet<Polyterm> Polyterms=new TreeSet<>(
      Comparator.comparing(Polyterm::getExponent));

  // below can be used to test input
  private Pattern in_test = Pattern.compile("([+|-]?([(\d+)|\d*(\.?=\d+)?])?(x(\^\d+)?)?)*");
}
