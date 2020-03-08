import java.io.PrintStream;

//package Project_1;
// import Scalar;
public class Polyterm  {
    Polyterm(Scalar constant,int power){
        coefficient=constant;
        exponent=power;
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
}
