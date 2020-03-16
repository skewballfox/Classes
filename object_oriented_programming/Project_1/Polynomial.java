import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Polynomial
{ //Note I'm probably going to rewrite tomorrow after the test
  //using either a linked list or an arraylist. Sorted Set is
  //just not a good fit for this problem
  public Polynomial(ArrayList<Polyterm> polylist)
  {
      Polyterms=polylist;
  }
  
  public Polynomial(String input) throws ArithmeticException
  {

       input=input.replaceAll("\\-","\\+\\-");
       
       for (String polystring : input.split("\\+"))
       {

           //Note, given that this is being supplied directly from the user
           //I think it would be okay to put a little faith in them and not
           //check that an item added has a duplicate exponent(x^2 and 4x^2)
           //I could simply copy paste some of the code from the add function,
           //however given that each call of the add is returning a new Polynomial
           //and I would have to do this for each item in the list, I don't think think
           //I should reuse the function unless I was modifying the set of Polyterms directly
           if (polystring.matches(in_test))
               Polyterms.add(new Polyterm(polystring));
           else {
               throw new ArithmeticException("ERROR: "+polystring+" is Not a valid Polynomial");
           }
        }
    }
  //private void sort()
  //{
  //    Polyterms.sort(Polyterm::getExponent);
  //}
  public Polynomial add(Polynomial addend)
  {
      ArrayList<Polyterm> tempPolyterms = new ArrayList<Polyterm>();
      Iterator<Polyterm> thisTerm = Polyterms.iterator();
      Iterator<Polyterm> thatTerm = addend.getPolyterms().iterator();
      boolean done=false;
      //NOTE: the only reason I went with this was because the contains method
      //uses o.equals(a), not o.compareto(a), and equality has to be for both
      //coefficient and expoenent.
      do{//iterate through both list at once, using exponent as comparator
          if (((Polyterm)thisText.next().compareTo((thatTerm)==-1){
              System.out.print("LEEROOY JEENKINS ");
              tempPolyterms.add((Polyterm)thisTerm);
              if (thisTerm.hasNext())
                  thisTerm.next();
              else {//all terms in temp have lower exponent than all terms left
                  while (thatTerm.hasNext())
                    tempPolyterms.add(thatTerm.next());
                  done=true;
              }
           }
           else if (((Polyterm)thisTerm).compareTo((Polyterm)thatTerm)==1)
           { 
              tempPolyterms.add((Polyterm)thatTerm);
              if (thatTerm.hasNext())
                  thatTerm.next();
              else {//all terms in temp have lower exponent than all terms left
                  while (thisTerm.hasNext())
                    tempPolyterms.add(thisTerm.next());
                  done=true;
              }
           }
           else
           { //the values have the same exponent
               tempPolyterms.add(((Polyterm)thisTerm).add((Polyterm)thatTerm));
               if (thisTerm.hasNext() && thatTerm.hasNext())
               {
                   thisTerm.next();
                   thatTerm.next();
               } else if (!thisTerm.hasNext() && !thatTerm.hasNext())
               {
                   done=true;
               } else if (thisTerm.hasNext())
               {
                   while (thisTerm.hasNext())
                       tempPolyterms.add(thisTerm.next());
                   done=true;
               } else
               {
                   while (thatTerm.hasNext())
                       tempPolyterms.add(thatTerm.next());
                   done=true;
               }
           }
          
      } while (!done);//on the plus side this is already sorted
      return new Polynomial(tempPolyterms);
  }

  public Polynomial mult(Polynomial multiplicand)
  {
      
      ArrayList<Polyterm> tempPolyterms = new ArrayList<Polyterm>();
      
      for (Polyterm term : multiplicand.getPolyterms())
          Polyterms.forEach((temp) -> tempPolyterms.add(temp.multi(term)));
      Collections.sort(tempPolyterms);
      return new Polynomial(tempPolyterms);
  }

  public Polynomial derive(){
      ArrayList<Polyterm> tempPolyterms = new ArrayList<Polyterm>();
      //tempPolyterms.addAll(Polyterms);
      
      Polyterms.forEach((term) -> tempPolyterms.add(term.derive()));
      if (tempPolyterms.get(0).getExponent()==0)//works so long as arraylist is sorted.
          tempPolyterms.remove(0);
      return new Polynomial(tempPolyterms);
  }
  
  public Scalar evaluate(Scalar multiplicand)
  {
      
      Scalar result = new Scalar(1);
      for (Polyterm term : Polyterms){
          result.add(term.evaluate(multiplicand));
      }
      return result.add(new Scalar(-1));
  }

  public ArrayList<Polyterm> getPolyterms(){
      return Polyterms;
  }

  public String toString(){
      String output="";
      for (Polyterm polytemp : Polyterms){
          output+="+"+polytemp.toString();
      }
      output=output.replaceAll("\\+\\-","\\-");

      return output;
  }
  private ArrayList<Polyterm> Polyterms = new ArrayList<Polyterm>();

  // below can be used to test input
  private static String in_test = "^-?(?:(\\d?)|(\\d+\\.\\d*)|(\\d*\\.\\d+))?(x|(x\\^(?=\\d+)\\d+))?$";
  //Note: this fat rail of a regex took me over an hour
  public static void main(String [] args)
  {
      System.out.println("test1");
      Polynomial test1=new Polynomial("1-7x+4x^2+8.3x^3");
      System.out.println("test2");
      Polynomial test2=new Polynomial("1-7x+4x^2+8.3x^3");
      System.out.println("test3");
      Polynomial test3=new Polynomial("1-7x+4x^2+8.3x^3");
      System.out.println("yeet");
      test1=test1.add(test2);

      System.out.println("test1: "+test1.toString());
      test3=test3.derive();
      System.out.println("test3: "+test3.toString());
      
  }
}
