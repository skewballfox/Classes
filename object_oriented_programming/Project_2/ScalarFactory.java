public class ScalarFactory {

    private static String inputTest = "^-?((\\d+)|(\\d+\\/\\d+)|(\\d+\\.\\d*)|(\\d*\\.\\d+))$";

    public static Scalar newScalar(String input) throws ArithmeticException{
        if (!input.matches(inputTest))
            throw new ArithmeticException("Error: "+input+" is not a valid scalar");
        String [] input_array;
        if (input.contains("/")){
            input_array=input.split("\\/");
            return new RationalScalar(Integer.valueOf(input_array[0]),
                                      Integer.valueOf(input_array[1]));
        }else{
            return new RealScalar(Double.valueOf(input));
        }
    }
    public static Scalar newScalar(int numerator, int denominator){
        return new RationalScalar(numerator,denominator);
    }
    public static Scalar newScalar(double value){
        return new RealScalar(value);
    }
    public static Scalar newScalar(int value){
        return new RealScalar(Double.valueOf(value));
    }
    public static void main(String [] args){
        //TODO
    }
}
