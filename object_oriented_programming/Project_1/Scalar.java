import java.util.regex.Pattern;
import java.util.regex.Matcher;

//public enum Scalar_types{integer, decimal, fraction, irrational};
public class Scalar {
    Scalar(string input) throws Exception
    {   
        string temp="";
        if (input.matches("[0-9]+(\\.[0-9]+)?+$"))//if only digits
        {
            this.value=input;
        }
        else//plan on adding support for fractions and irrationals
        {
            throw new Exception("invalid input");
        }
    }

    public void add(Scalar addend){
        //if this.scalarType==addend.get_type()
        if (value.contains(".") or (addend.get_value()).contains(".")){
            this.value=String.valueOf(Double.valueOf(value)+Double.valueof(addend.get_value()));
        } else { //add support for other stuff later
            this.value=String.valueOf(Integer.valueOf(value)+integer.valueof(addend.get_value()));
        }
    }   

    public void multi(Scalar multiplicand)
    {
        if (value.contains(".") or (addend.get_value()).contains(".")){
            this.value=String.valueOf(Double.valueOf(value)*Double.valueof(addend.get_value()));
        } else { //add support for other stuff later
            this.value=String.valueOf(Integer.valueOf(value)*integer.valueof(addend.get_value()));
        }
    }
    public void multi(int multiplicand)//for deriving
    {
        if (value.contains(".") ){
            this.value=String.valueOf(Double.valueOf(value)*(Double)multiplicand);
        } else { //add support for other stuff later
            this.value=String.valueOf(Integer.valueOf(value)*multiplicand);
        }
    }
    public void pow(int exponent)
    {
        this.value;
    }

    public void neg()
    {
        this.value*=-1;
    }

    public void equals(Scalar comparator);


    public string get_value(){
        return value;
    }

    private void convert(string );

    private string value;//split into reducible and irriducible components
    private string fractional_component;
    private string irriducible_component;
    //private ScalarTypes scalarType;    
    
}