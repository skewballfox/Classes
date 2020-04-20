import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Polynomial
{ 
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
        Polyterms.sort(null);

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
      Polyterm thisTerm = (Polyterm)thisIter.next();
      Polyterm thatTerm = (Polyterm)thatIter.next();
      
      //I tried inverting the nested conditionals to make the code more condensed,
      //and I ran into an issue with certain values being skipped over
      do{//iterate through both list at once, using exponent as comparator
      
          if (thisTerm.compareTo(thatTerm)==-1) {

              tempPolyterms.add(thisTerm);

              if (thisIter.hasNext())
                  thisTerm=(Polyterm)thisIter.next();
              else {
                  tempPolyterms.add((Polyterm)thatTerm);
                  thatIter.forEachRemaining((term) -> tempPolyterms.add((Polyterm)term));
                  done=true;
              }

          } else if (thisTerm.compareTo(thatTerm)==1) {

              tempPolyterms.add(thatTerm);

              if (thatIter.hasNext())
                  thatTerm=(Polyterm)thatIter.next();
              else{
                  tempPolyterms.add((Polyterm)thisTerm);
                  thisIter.forEachRemaining((term) -> tempPolyterms.add((Polyterm)term));
                  done=true;
              }

          } else {//both are equal
              tempPolyterms.add((thisTerm).add(thatTerm));

              if (thisIter.hasNext() && thatIter.hasNext()) {
                  thisTerm=(Polyterm)thisIter.next();
                  thatTerm=(Polyterm)thatIter.next();
              } else if (thisIter.hasNext()){
                  thisIter.forEachRemaining((term) -> tempPolyterms.add((Polyterm)term));   
                  done=true;
              } else if (thatIter.hasNext()) {
                  thatIter.forEachRemaining((term) -> tempPolyterms.add((Polyterm)term));   
                  done=true;                      
              } else {
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
      
      Scalar result = SF.newScalar(0);
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
      output=output.substring(1);
      output=output.replaceAll("\\+\\-","\\-");

      return output;
  }
  private ArrayList<Polyterm> Polyterms = new ArrayList<Polyterm>();
  private final ScalarFactory SF= new ScalarFactory();

  // below can be used to test input
  private static String in_test = "^-?(?:(\\d+)|(\\d+\\/\\d+)|(\\d+\\.\\d*)|(\\d*\\.\\d+))?(x|(x\\^(?=\\d+)\\d+))?$";
  //Note: this fat rail of regex took me over an hour
  public static void main(String [] args)
  {
      ScalarFactory SF =new ScalarFactory();
      System.out.println("test1");
      Polynomial test1=new Polynomial("-7x+4x^2+8.3x^3");
      System.out.println("test2");
      Polynomial test2=new Polynomial("-7x+4x^2+8.3x^3");
      System.out.println("test equals: "+String.valueOf(test1.equals(test2)));
      Polynomial test3=new Polynomial("1-7x+4x^2+8.3x^3");
      Polynomial test4=new Polynomial("1+4x^2");
      Polynomial test5=new Polynomial("1-7x+3/7x^9");
      Polynomial test6=new Polynomial("1-7x+4x^2+8.3x^3+x^4+1/2x^9");
      Polynomial test7=new Polynomial("1/2-7/3x+4x^2+8.3x^3");
      Polynomial test8=new Polynomial("1-7x+4x^2+8.3x^3");
      Polynomial test9=new Polynomial("1-7x+4x^2+8.3x^3");
      Polynomial test10=new Polynomial("1-7x+4x^2+8.3x^3");
      
      test1=test1.add(test2);

      System.out.println("test1: "+test1.toString());
      test3=test3.derivative();
      System.out.println("test3: "+test2.toString());
      Scalar temp= SF.newScalar(4);
      temp=test2.evaluate(temp);
      System.out.println(temp.toString());
      System.out.println("test4: "+(test2.evaluate(temp)).toString());
      System.out.println((test4.add(test5)).toString());//yep
      System.out.println("test6: "+(test4.add(test4)).toString());
      System.out.println("test7: "+(test5.add(test6)).toString());

      System.out.println("test4: "+(test2.evaluate(temp)).toString());      
  }
}
