import edu.princeton.cs.algs4.StdOut;

public class Rational {

    private int numerator;
    private int denominator;
    private static int MAX = Integer.MAX_VALUE;

    public Rational(int numerator, int denominator) {
        if (denominator == 0)
            throw new ArithmeticException("Divide by zero");
        int common = gcd(numerator, denominator);
        this.numerator = numerator / common;
        this.denominator = denominator / common;
    }

    private static int gcd(int p, int q) {
        if(p == 0) return 1;
        p = Math.abs(p);
        q = Math.abs(q);
        if(q != 0) return gcd(q, p % q);
        else return p;
    }

    public Rational plus(Rational b) {
        assert Math.abs(numerator * b.denominator + b.numerator * denominator) < MAX &&
                Math.abs(denominator * b.denominator) < MAX : "The number overflows";
        return new Rational(numerator * b.denominator + b.numerator * denominator,
                denominator * b.denominator);
    }

    public Rational minus(Rational b) {
        //The assert will never be called
        assert Math.abs(numerator * b.denominator - b.numerator * denominator) < MAX &&
                Math.abs(denominator * b.denominator) < MAX : "The number overflows";
        return new Rational(numerator * b.denominator - b.numerator * denominator,
                denominator * b.denominator);
    }

    public Rational times(Rational b) {
        assert Math.abs(numerator * b.numerator) < MAX &&
                Math.abs(denominator * b.denominator) < MAX : "The number overflows";
        return new Rational(numerator * b.numerator,
                denominator * b.denominator);
    }

    public Rational divides(Rational b) {
        return times(new Rational(b.denominator, b.numerator));
    }

    @Override
    public boolean equals(Object x) {
        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;
        Rational that = (Rational)x;
        return this.numerator == that.numerator && this.denominator == that.denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(4, 5),
                r2 = new Rational(5, 6);
        StdOut.println(r1.plus(r2));
        StdOut.println(r1.minus(r2));
        StdOut.println(r1.times(r2));
        StdOut.println(r1.divides(r2));
    }
}
