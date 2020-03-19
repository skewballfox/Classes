import java.util.Scanner;
import java.lang.reflect.Method;

//this is ugly and hacky and probably bad design in terms of
//a OOP but I needed to write this quick. I'll clean up a bit for my
//next assignment.

public class Calculator {
    Calculator(){
        Operation Op;
        while(true){
          promptOp();
          Op=getOp();
          if ( Op.ordinal() == 0 || Op.ordinal() == 1 ){
              firstPolynomial=getPolynomial("first ");
              secondInput=getPolynomial("second ");
          } else if (Op.ordinal() == 2){
              firstPolynomial=getPolynomial("");
              secondInput=getScalar();
          } else if (Op.ordinal() == 3 ){
              firstPolynomial=getPolynomial("");
          } else {
              System.exit(0);
          }
          result=getResult(Op);
          printResult();
          
        }
       
    }
    public void promptOp(){
        System.out.println("Please select an operation:\n");
        System.out.println("\t\t1.Addition");
        System.out.println("\t\t2.Multiplication");
        System.out.println("\t\t3.Evaluation");
        System.out.println("\t\t4.Derivative");
        System.out.println("\t\t5.Exit");
    }
    public Operation getOp(){
        String input;
        do{            
            input = prompt.nextLine();

            if ("12345".contains(input) && input.length() == 1)
                return Operation.values()[Integer.valueOf(input)-1];
            else
            {
                for (Operation op : Operation.values())
                {
                    if (op.name().equals(input.toLowerCase()))
                        return op;
                }//if you made it this far it is neither a valid digit nor operation
                System.out.println("that response was not valid, please try again.\n");
            }
        }while(true);
    }

    public Polynomial getPolynomial(String specifier){
        String input;
        do{
            System.out.println("Please enter the "+specifier+"Polynomial");
            try{
                input=prompt.nextLine();
                return new Polynomial(input);
            } catch (Exception ArithmeticException){
                System.out.println("Not a valid polynomial, Please try again\n");
            }
                
        }while(true);
    }
    public Scalar getScalar()
    {
        String input;
        while(true)
        {
            System.out.println("Please enter a scalar to be used for evalutation");
            try{
                input=prompt.nextLine();
                return new Scalar(input);
            }catch (Exception ArithmeticException){
                System.out.println("Not a valid Scalar, Please try again\n");
            }
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


    private Scanner prompt= new Scanner(System.in);
    //may switch this out with a list to add history later
    private Polynomial firstPolynomial;
    private Object secondInput;//could be poly or scalar
    private Object result;
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
