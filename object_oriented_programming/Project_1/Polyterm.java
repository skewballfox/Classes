import java.io.PrintStream;

import Scalar.Scalar;
public class Polyterm  {
    
    void add(Polyterm addend){
            CheckExponent(addend);
            return new Polyterm(coefficient+addend.get_coefficient(),exponent);
    } 
    

    void multi(Polyterm multiplicand) {
             CheckExponent(addend);
            return new Polyterm(coefficient.multi(multiplicand.get_coefficient()),exponent);
    }

    void pow(int new_exponent)
    {
        return new Polyterm(coefficient.pow(new_exponent),exponent*new_exponent);
    }

    

    boolean equals(Polyterm comparator)
    {
        if (this.coefficient==comparator.get_coefficient() && this.exponent==comparator.get_exponent()){
            return true;
        } else {
            return false;
        }
    }

    void derive()
    {
        if (this.exponent!=0){
            return new Polyterm((coefficient.multiplicand(Double.valueOf(exponent))),exponent-1);
        }
        else {
            return new Polyterm((new Scalar(0)),0)
        }
    }

    int get_exponent()
    {
        return exponent;
    }
    
    Scalar get_coefficient()
    {
        return coefficient();
    }

    private Scalar coefficient;
    private int exponent;

    private CheckExponent(Polyterm other) Throws Exception {
      if (exponent!=other.get_exponent())
          throw new Exception MismatchedExponentError("the exponents for these polyterms do not match");
    }
}
