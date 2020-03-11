import java.io.PrintStream;

//package Project_1;
// import Scalar;
public class Polyterm  {
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
            exponent=Integer.valueOf(input.split("x^")[-1]);
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
            return new Polyterm(coefficient.add(addend.get_coefficient()),exponent);
    } 
    

    public Polyterm multi(Polyterm multiplicand) {
             CheckExponent(multiplicand);
            return new Polyterm(coefficient.multi(multiplicand.get_coefficient()),exponent);
    }

    public Polyterm pow(int new_exponent)
    {
        return new Polyterm(coefficient.pow(new_exponent),exponent*new_exponent);
    }

    

    public boolean equals(Polyterm comparator)
    {
        if (this.coefficient==comparator.get_coefficient() && this.exponent==comparator.get_exponent()){
            return true;
        } else {
            return false;
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

    public int get_exponent()
    {
        return exponent;
    }
    
    public Scalar get_coefficient()
    {
        return coefficient;
    }

    private Scalar coefficient;
    private int exponent;

    private void CheckExponent(Polyterm other) throws ArithmeticException {
      if (exponent!=other.get_exponent())
          throw new ArithmeticException("the exponents for these polyterms do not match");
    }
    public static void main(String [] args)
    {
        String[] input_test={"7","7x","7x^2","x^2","x"};
        for (String input : input_test)
        {
            Polyterm temp=new Polyterm(input);
            System.out.println(temp.get_coefficient()+" "+temp.get_exponent());
        }
    }
}
