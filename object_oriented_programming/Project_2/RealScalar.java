import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RealScalar implements Scalar {
    public RealScalar(double input)
    {  
        value=input;
    }
    
    @Override
    public RealScalar add(Scalar addend){
        if (getClass()!=addend.getClass())
            addend=addend.convert(getClass());
        return new RealScalar(value+((RealScalar)addend).getValue());
    }

    @Override
    public RealScalar multi(Scalar multiplicand)
    {
    if (getClass()!=multiplicand.getClass())
            multiplicand=multiplicand.convert(getClass());
        return new RealScalar(value*((RealScalar)multiplicand).getValue());
    }

    @Override
    public RealScalar multi(int multiplicand)//for deriving
    {
                return new RealScalar(this.value*Double.valueOf(multiplicand));
    }

    @Override
    public RealScalar pow(int exponent)
    {
        return new RealScalar(Math.pow(this.value,exponent));
    }

    @Override
    public Scalar neg()
    {
        return new RealScalar(this.value*(-1.0));
    }

    @Override
    public boolean equals(Scalar comparator)
    {
      if (Math.abs(value-((RealScalar)comparator).getValue())<this.threshhold)
         return true;
      else
         return false;
    }

    public double getValue(){
        return this.value;
    }

    @Override
    public String toString()
    {
        if (Math.abs(value-Math.round(this.value))<this.threshhold)//if everything to the right of . is 0
            return String.valueOf(Math.round(this.value));
        else
            return String.valueOf(this.value);
    }

    @Override
    public Scalar convert(Class<?> scalarType) throws UnsupportedOperationException {
        if (scalarType==RationalScalar.class){
            if (Math.abs(this.value-Math.round(this.value))<this.threshhold)
            {//if it is an integer in every way but value
                return new RationalScalar((int)this.value,1);

            } else
            {
                String [] tempArray=this.toString().split("\\.");
                String tempDenom="1";

                for (char decimalPlace : (tempArray[1]).toCharArray())//I'm not sure why this was necessary
                    tempDenom+="0";

                return new RationalScalar(Integer.valueOf(tempArray[1])+(
                    Integer.valueOf(tempArray[0])*Integer.valueOf(tempDenom)),
                    Integer.valueOf(tempDenom));
            }
        } else
        {
            throw new UnsupportedOperationException("%s is not a type of scalar".format(String.valueOf(scalarType)));
        }
    }

    //private RealScalar convert(Scalar s);

    private final double threshhold=.0001;
    private double value;    

    public static void main(String [] args){
      ScalarFactory SF=new ScalarFactory();
      Scalar test_1= SF.newScalar(1);
      Scalar test_2= SF.newScalar(3.5);
      Scalar test_3= test_1.add(test_2);
      Scalar test_4= test_1.multi(test_3);
      Scalar test_5= test_3.neg();
      System.out.format("scalar test_1: %s scalar test_2: %s scalar test_3: %s scalar test_4: %s scalar test_5: %s\n",test_1.toString(),test_2.toString(),test_3.toString(),test_4.toString(),test_5.toString());
    }
}
