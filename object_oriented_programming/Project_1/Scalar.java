abstract class Scalar {

    abstract void add(Scalar addend);

    abstract void multi(Scalar multiplicand);

    abstract void pow(int exponent);

    abstract void neg();

    abstract void equals(Scalar comparator);

    abstract void derive();
}