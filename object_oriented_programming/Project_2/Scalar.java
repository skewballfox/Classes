
public interface Scalar {

    public Scalar add(Scalar addend);

    public Scalar multi(Scalar multiplicand);

    public Scalar multi(int multiplicand);

    public Scalar pow(int exponent);

    public Scalar neg();

    public boolean equals(Scalar comparator);

    public String toString();

    public Scalar convert(Class<?> scalarType);
}
