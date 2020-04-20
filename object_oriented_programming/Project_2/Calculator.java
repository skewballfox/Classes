import java.util.Scanner;
import java.lang.reflect.Method;

//this is ugly and hacky and probably bad design in terms of
//a OOP but I needed to write this quick. I'll clean up a bit for my
//next assignment.

public class Calculator {
    Calculator(){
        Operation Op;
        while(true){
          Op=promptOp();
          promptValues(Op);
          result=getResult(Op);
          printResult();
          
        }
       
    }
    public Operation promptOp(){
        String input;
        do {//keep looping until you get a valid response

                System.out.println("Please select an operation:\n");
                System.out.println("\t\t1.Addition");
                System.out.println("\t\t2.Multiplication");
                System.out.println("\t\t3.Evaluation");
                System.out.println("\t\t4.Derivative");
                System.out.println("\t\t5.Exit");
            try{
                input = prompt.nextLine();
               return setOp(input);
            } catch(IllegalArgumentException e){
              System.out.println("invalid response please try again");
            }
        } while (true);
    }
    
    public Operation setOp(String input) throws IllegalArgumentException {
           
        if ("12345".contains(input) && input.length() == 1)
            return Operation.values()[Integer.valueOf(input)-1];
        else
        {
            for (Operation op : Operation.values())
            {
                if (op.name().equals(input.toLowerCase()))
                    return op;
            }//if you made it this far it is neither a valid digit nor operation
            throw new IllegalArgumentException("Invalid response, please try again");
        }
        
    }
    public void promptValues(Operation Op){
          if ( Op.ordinal() == 0 || Op.ordinal() == 1 ){
              System.out.println("Please enter the first polynomial");
              murpheysInput("first");
              System.out.println("Please enter the second polynomial");
              murpheysInput("polynomial");
          } else if (Op.ordinal() == 2){
              System.out.println("Please enter the polynomial to evaluate");
              murpheysInput("first");
              System.out.println("Please enter the value for x");
              murpheysInput("scalar");
          } else if (Op.ordinal() == 3 ){
              System.out.println("Please enter the polynomial to derive");
              murpheysInput("first");
          } else {
              System.exit(0);
          }
    }
               
       
    public Object getResult(Operation Op){
        //no idea if this will work
        switch(Op){
            case addition:
                return firstPolynomial.add((Polynomial)secondInput);
            case multiplication:
                return firstPolynomial.mult((Polynomial)secondInput);
            case evaluation:
                return firstPolynomial.evaluate((Scalar)secondInput);
            case derivative:
                return firstPolynomial.derivative();
        }
        return result;
    }

    public void printResult()
    {

        System.out.println("The solution is:\n");
        System.out.println(result.getClass().getName());
        if (result instanceof Polynomial)
        {
            Polynomial res = (Polynomial) result;
            System.out.println(res.toString());
        }
    }

    private void murpheysInput(String input_type)
    {
        String input;
        boolean valid=false;
        do {
            try{
                input=prompt.nextLine();

                if (input_type=="first") {
                    firstPolynomial = new Polynomial(input);
                } else if (input_type=="polynomial") {
                    secondInput = new Polynomial(input);
                } else {
                    secondInput = SF.newScalar(input);
                }
                valid=true;
 
            } catch (Exception ArithmeticException){
                System.out.println("your input was invalid, Please try again\n");
            }
        }while(!valid);

    }
    private Scanner prompt= new Scanner(System.in);
    //may switch this out with a list to add history later
    private Polynomial firstPolynomial;
    private Object secondInput;//could be poly or scalar
    private Object result;
    private final ScalarFactory SF = new ScalarFactory();
    private static enum Operation{
        addition,
        multiplication,
        evaluation,
        derivative,
        exit
    }
    public static void main(String [] args)
    {
        Calculator calc=new Calculator();
    }
}
