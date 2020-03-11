

//public enum Scalar_types{integer, decimal, fraction, irrational};
public class Scalar {
    Scalar(double input)
    {  
        value=input;
    }
    Scalar(String input)
    {
        value=Double.valueOf(input);
    }
    public Scalar add(Scalar addend){
        return new Scalar(value+addend.get_value());
    }

    public Scalar multi(Scalar multiplicand)
    {
        return new Scalar(value*multiplicand.get_value());
    }
    public Scalar multi(int multiplicand)//for deriving
    {
        return new Scalar(value*Double.valueOf(multiplicand));
    }
    public Scalar pow(int exponent)
    {
        return new Scalar(Math.pow(value,exponent));
    }
      public Scalar neg()
    {
        return new Scalar(value*(-1.0));
    }

    public boolean equals(Scalar comparator)
    {
      if (this.value==comparator.get_value())
         return true;
      else
         return false;
    }


    public double get_value(){
        return value;
    }
    public String toString()
    {
        return String.valueOf(value);
    }

    private double value;//split into reducible and irriducible components
    //private ScalarTypes scalarType;    
    public static void main(String [] args){
      Scalar test_1= new Scalar(1);
      Scalar test_2= new Scalar(3.5);
      Scalar test_3= test_1.add(test_2);
      Scalar test_4= test_1.multi(test_3);
      Scalar test_5= test_3.neg();
      System.out.format("scalar test_1: %f scalar test_2: %f scalar test_3: %f scalar test_4: %f scalar test_5: %f\n",test_1.get_value(),test_2.get_value(),test_3.get_value(),test_4.get_value(),test_5.get_value());
    }
}
