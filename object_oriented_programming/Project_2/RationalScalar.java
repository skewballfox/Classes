import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RationalScalar implements Scalar {
    public RationalScalar(int numerator, int denominator) throws ArithmeticException
    {
        if (denominator!=0) {
            this.numerator=numerator;
            this.denominator=denominator;
        } else {
            throw new ArithmeticException("Denominator for fraction cannot be zero");
        }
    }
    
    @Override
    public RationalScalar add(Scalar addend){
        int resultNumerator,resultDenominator;
        if (addend.getClass()!=getClass()) {
            addend=addend.convert(getClass());
        }
        resultNumerator=numerator*((RationalScalar)addend).getDenominator() +
            denominator*((RationalScalar)addend).getNumerator();
        resultDenominator=denominator*((RationalScalar)addend).getDenominator();
        return new RationalScalar(resultNumerator,resultDenominator);
    }

    @Override
    public RationalScalar multi(Scalar multiplicand)
    {
        if (multiplicand.getClass()!=getClass()) {
            multiplicand=multiplicand.convert(getClass());
        }

        return new RationalScalar(numerator * ((RationalScalar)multiplicand).getNumerator(), denominator * ((RationalScalar)multiplicand).getDenominator());
    }

    @Override
    public RationalScalar multi(int multiplicand)//for deriving
    {
        return new RationalScalar(numerator*multiplicand,denominator);
    }

    @Override
    public RationalScalar pow(int exponent)
    {
        return new RationalScalar((int)(Math.pow(numerator,exponent)),
                                  (int)(Math.pow(denominator,exponent)));
    }

    @Override
    public RationalScalar neg()
    {
        return new RationalScalar(numerator*(-1),denominator);
    }

    @Override
    public boolean equals(Scalar comparator)
    {
        if (getClass()!=comparator.getClass())
            return false;
        if (numerator == ((RationalScalar)comparator).getNumerator() &&
            denominator == ((RationalScalar)comparator).getDenominator())
            return true;
        else
            return false;
    }

    @Override
    public String toString()
    {
        reduce();
        if ( denominator == 1 )//if everything to the right of . is 0
            return String.valueOf(numerator);
        else {
            return String.valueOf(numerator)+"/"+String.valueOf(denominator);
        }
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }
    public Scalar convert(Class<?> scalarType) throws UnsupportedOperationException{
        if (scalarType==RealScalar.class) {
            return new RealScalar(Double.valueOf(numerator)/Double.valueOf(denominator));
        } else {
            throw new UnsupportedOperationException("%s is not a type of scalar".format(String.valueOf(scalarType)));
        }
    }
    private int gcd(int a,int b){
     if (b==0)
         return Math.abs(a);
     else
        return gcd(b,a%b); 
    }

    private void reduce(){
        int gcd=gcd(numerator,denominator);
        if (gcd!=1){
            this.numerator=numerator/gcd;
            this.denominator=denominator/gcd;
        }
    }

    private int numerator,denominator;
    private static String inputTest = "^-?((\\d+)|(\\d+\\.\\d*)|(\\d*\\.\\d+))$";
    //private ScalarTypes scalarType;    
    public static void main(String [] args){
      Scalar test_1= new RationalScalar(1,1);
      Scalar test_2= new RationalScalar(3,5);
      Scalar test_3= test_1.add(test_2);
      Scalar test_4= test_1.multi(test_3);
      Scalar test_5= test_3.neg();
      System.out.format("scalar test_1: %s scalar test_2: %s scalar test_3: %s scalar test_4: %s scalar test_5: %s\n",test_1.toString(),test_2.toString(),test_3.toString(),test_4.toString(),test_5.toString());
    }
}
