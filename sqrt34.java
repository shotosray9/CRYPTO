import java.math.BigInteger;
import java.util.Scanner;

public class sqrt34 {

    public static BigInteger findR(BigInteger x, BigInteger n) {
        return x.modPow(new BigInteger("2"), n);
    }

    public static BigInteger[] findRoots(BigInteger a, BigInteger p) {
        BigInteger exp = p.add(new BigInteger("1"));
        exp = exp.divide(new BigInteger("4"));
        BigInteger[] bigInts = new BigInteger[2];
        bigInts[0] = a.modPow(exp, p); // x1
//        System.out.println(bigInts[0]);
        bigInts[1] = p.subtract(bigInts[0]);
//        System.out.println(bigInts[1]);
//        System.out.println(bigInts[0] + " " + bigInts[1]);
        return bigInts;
    }

    // solve Extended Euclidean
    public static BigInteger[] solve(BigInteger a, BigInteger b) {
        BigInteger x = new BigInteger("0");// y = 1, lastx = 1, lasty = 0, temp;
        BigInteger y = new BigInteger("1");
        BigInteger lastx = new BigInteger("1");
        BigInteger lasty = new BigInteger("0");
        BigInteger temp = new BigInteger("0");

        while (!b.equals(new BigInteger("0"))) {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);

            a = b;
            b = r;

            temp = x;
            x = lastx.subtract(q.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(q.multiply(y));
            lasty = temp;
        }
//        System.out.println("Roots  x : " + lastx + " y :" + lasty);
        BigInteger[] roots = new BigInteger[2];
        roots[0] = lastx;
        roots[1] = lasty;
        return roots;
    }

    public static BigInteger solveCRT(BigInteger m, BigInteger n, BigInteger c, BigInteger d, BigInteger a, BigInteger b){
        BigInteger x1 = (n.multiply(c).multiply(a)).add(m.multiply(d).multiply(b));
//        System.out.println(x1);
        return x1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger p = new BigInteger(sc.nextLine());
        BigInteger q = new BigInteger(sc.nextLine());
        BigInteger x = new BigInteger(sc.nextLine());
        BigInteger n = p.multiply(q);
        BigInteger r = findR(x, n);
//        System.out.println(r);
//        System.out.println(n);


        BigInteger[] from_p = findRoots(r, p);
//        System.out.println();
        BigInteger[] from_q = findRoots(r, q);
        BigInteger[] extEuc = solve(p, q);
//        if (extEuc[0].signum() != 1){
//            extEuc[0] = q.add(extEuc[0]);
//
//            System.out.println(extEuc[0]);
//        }
//        if(extEuc[1].signum() != 1){ // goes if is positive
//            extEuc[1] = q.add(extEuc[1]);
//            System.out.println(extEuc[1]);
//        }

        BigInteger x1 = solveCRT(p, q, extEuc[1], extEuc[0], from_p[0], from_q[1]);
        x1 = x1.mod(n);
        if (x1.equals(x) || x.equals(n.subtract(x1))) {
            BigInteger x2 = solveCRT(p, q, extEuc[1], extEuc[0], from_p[1], from_q[1]);
            x2 = x2.mod(n);
            if (x2.compareTo(n.subtract(x2)) == -1) {
                System.out.println(x2);
                System.out.println(n.subtract(x2));
            }
            else {
                System.out.println(n.subtract(x2));
                System.out.println(x2);
            }
        }
        else {
            if (x1.compareTo(n.subtract(x1)) == -1) {
                System.out.println(x1);
                System.out.println(n.subtract(x1));
            }
            else {
                System.out.println(n.subtract(x1));
                System.out.println(x1);
            }

        }


    }
}


/*
* x1 = 190
* x2 = 241
*
* x3 = 2891
* x4 = 6828
*
*
* */