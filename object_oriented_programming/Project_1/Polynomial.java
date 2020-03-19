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
           if (polystring.matches(in_test)){
               if (polystring.length()!=0)//handle case of leading negative digit
                  Polyterms.add(new Polyterm(polystring));
           }
           else {
               throw new ArithmeticException("ERROR: "+polystring+" is Not a valid Polynomial");
           }
        }
    }


  public Polynomial add(Polynomial addend)
  {
      ArrayList<Polyterm> tempPolyterms = new ArrayList<Polyterm>();
      Iterator<Polyterm> thisIter = Polyterms.iterator();
      Iterator<Polyterm> thatIter = addend.getPolyterms().iterator();
      boolean done=false;
      //NOTE: the only reason I went with this was because the contains method
      //uses o.equals(a), not o.compareto(a), and equality has to be for both
      //coefficient and expoenent.
      do{//iterate through both list at once, using exponent as comparator
          if (thisIter.hasNext() && thatIter.hasNext())
          {//if both have stuff left compare the values, add values with same exponent
              Polyterm thisTerm = (Polyterm)thisIter.next();
              Polyterm thatTerm = (Polyterm)thatIter.next();

              if (thisTerm.compareTo(thatTerm)==-1)
                  tempPolyterms.add(thisTerm);
              else if (thisTerm.compareTo(thatTerm)==1)
                  tempPolyterms.add(thatTerm);
              else
                  tempPolyterms.add((thisTerm).add(thatTerm));                  
      
          }
          else if (!thisIter.hasNext() && !thatIter.hasNext())
          {//if both are empy
              done=true;
          }//next two cases work because if one list is empty then all remaining exponents are larger
          else if (thisIter.hasNext())
          {
              thisIter.forEachRemaining((term) -> tempPolyterms.add(term));   
              done=true;
          }
          else
          {
              thatIter.forEachRemaining(term -> tempPolyterms.add(term));
              done=true;
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

  public Polynomial derivative(){
      ArrayList<Polyterm> tempPolyterms = new ArrayList<Polyterm>();
      //tempPolyterms.addAll(Polyterms);
      
      Polyterms.forEach((term) -> tempPolyterms.add(term.derive()));
      if (tempPolyterms.get(0).getExponent()==0)//works so long as arraylist is sorted.
          tempPolyterms.remove(0);
      return new Polynomial(tempPolyterms);
  }
  
  public Scalar evaluate(Scalar multiplicand)
  {
      
      Scalar result = new Scalar(0);
      for (Polyterm term : Polyterms){
          result=result.add(term.evaluate(multiplicand));
      }
      return result;
  }

  public ArrayList<Polyterm> getPolyterms(){
      return Polyterms;
  }
  public boolean equals(Polynomial other)
  {   
      return Polyterms.equals(other.getPolyterms()); 
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
  private static String in_test = "^-?(?:(\\d+)|(\\d+\\.\\d*)|(\\d*\\.\\d+))?(x|(x\\^(?=\\d+)\\d+))?$";
  //Note: this fat rail of regex took me over an hour
  public static void main(String [] args)
  {
      System.out.println("test1");
      Polynomial test1=new Polynomial("-7x+4x^2+8.3x^3");
      System.out.println("test2");
      Polynomial test2=new Polynomial("-7x+4x^2+8.3x^3");
      System.out.println("test equals: "+String.valueOf(test1.equals(test2)));
      Polynomial test3=new Polynomial("1-7x+4x^2+8.3x^3");
      System.out.println("yeet");
      test1=test1.add(test2);

      System.out.println("test1: "+test1.toString());
      test3=test3.derivative();
      System.out.println("test3: "+test2.toString());
      Scalar temp= new Scalar(4);
      temp=test2.evaluate(temp);
      System.out.println(temp.toString());
      System.out.println("test4: "+(test2.evaluate(temp)).toString());
      
  }
}
