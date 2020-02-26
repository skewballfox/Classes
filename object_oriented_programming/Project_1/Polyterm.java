import java.io.PrintStream;

import Scalar.Scalar;
public class Polyterm  {
    
    void add(Polyterm addend){
        try {
            if (this.exponent == addend.get_exponent())
            {
                this.coefficient+=addend.get_coefficient();
            }else
            {
                System.out.println("oops");
            }
        } catch (Exception e) {
            System.out.print("oops");
        } 
    }

    void multi(Polyterm multiplicand) {
        this.coefficient*=multiplicand.get_coefficient();
        this.exponent+=multiplicand.get_coefficient();
    }

    void pow(int new_exponent)
    {
        this.exponent+=new_exponent;
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
            this.coefficient*=(Double)this.exponent;
            this.exponent-=1;
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
}