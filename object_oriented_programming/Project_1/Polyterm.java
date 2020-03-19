import java.io.PrintStream;

//package Project_1;
// import Scalar;
public class Polyterm implements Comparable<Polyterm>{
    Polyterm(Scalar constant,int power){
        coefficient=constant;
        exponent=power;
    }

    Polyterm(String input)
    {
        //Note: given possible inputs of 7, 7x, x, 7x^2, and x^2
        //x is in 4 or 5 options, \dx is in 3 possible options
        //and x^\d is in 2 possible options, so a process of elimation works best 
        if (!input.contains("x"))//meaning 7
        {
            coefficient = new Scalar(input);
            exponent = 0;
            return;
        }
        else if (input.indexOf("x") == 0)//meaning x or x^2 
        {
          coefficient = new Scalar(1);
        }//so it's either 7x or 7x^2 
        else
        {
            coefficient= new Scalar(input.split("x")[0]);  
        }//at this point all possible coefficients are accounted for
        if (input.contains("^"))
        {
            String [] input_array=input.split("\\^");//because negative indexing isn't allowed, and this is a regex, not a string
            exponent=Integer.valueOf(input_array[input_array.length-1]);
            return;
        }
        else
        {
            exponent = 1;
            return;
        }
    }

    public Polyterm add(Polyterm addend){
            CheckExponent(addend);
            return new Polyterm(coefficient.add(addend.getCoefficient()),exponent);
    } 
    

    public Polyterm multi(Polyterm multiplicand) {
            return new Polyterm(coefficient.multi(multiplicand.getCoefficient()),exponent+multiplicand.getExponent());
    }

    public Polyterm pow(int new_exponent)
    {
        return new Polyterm(coefficient.pow(new_exponent),exponent*new_exponent);
    }

    @Override
    public boolean equals(Object comparator)
    {
        if (this == comparator)
            return true;
        else if (comparator == null || getClass()!=comparator.getClass())
            return false;
        else{
            Polyterm other=(Polyterm) comparator;
            System.out.println(this.toString()+" "+other.toString());
            if (this.coefficient==other.getCoefficient() && this.exponent==other.getExponent()){
                return true;
            } else {
                return false;
            }
        }
    }

    public Polyterm derive()
    {
        if (this.exponent!=0){
            return new Polyterm(coefficient.multi(exponent),exponent-1);
        }
        else {
            return new Polyterm((new Scalar(0)),0);
        }
    }

    public int getExponent()
    {
        return exponent;
    }
    
    public Scalar getCoefficient()
    {
        return coefficient;
    }
    public Scalar evaluate(Scalar multiplicand)
    {
        if (exponent!=0)
            return coefficient.multi(multiplicand.pow(exponent));
        else
            return coefficient;
    }
    public String toString()
    {
        String display="";
        if (coefficient.get_value()!=1.0 || exponent == 0){//this should include a 1 constant value (ie exponent 0)
            display+=coefficient.toString();
        }
        if (exponent>1){
            display+="x^"+String.valueOf(exponent);
        }else if (exponent == 1){
            display+="x";
        }//if exponent=0 you are already done
        return display;
    }

    @Override
    public int compareTo(Polyterm other)
    {
        return this.exponent.compareTo(other.getExponent());
    }

    
    private Scalar coefficient;
    private Integer exponent;

    private void CheckExponent(Polyterm other) throws ArithmeticException {
      if (exponent!=other.getExponent())
          throw new ArithmeticException("the exponents for these polyterms do not match");
    }

        
    public static void main(String [] args)
    {
        String[] input_test={"7","7x","7x^2","x^2","x","1"};
        for (String input : input_test)
        {
            Polyterm temp = new Polyterm(input);
            System.out.println("this Polyterm is: "+temp.toString());
        }
    }
}
