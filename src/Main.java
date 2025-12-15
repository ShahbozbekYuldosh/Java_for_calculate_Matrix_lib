import matrix.CalculateMatrix;

public class Main {
    public static void main(String[] args) {
        // ===============================
        // Example 1: Matritsa yaratish va elementlarni qo'shish
        // ===============================
        CalculateMatrix A = new CalculateMatrix(2, 2);
        A.set(0, 0, 1); A.set(0, 1, 2);
        A.set(1, 0, 3); A.set(1, 1, 4);
        System.out.println("Matrix A:");
        System.out.println(A);

        CalculateMatrix B = new CalculateMatrix(2, 2);
        B.set(0, 0, 5); B.set(0, 1, 6);
        B.set(1, 0, 7); B.set(1, 1, 8);
        System.out.println("Matrix B:");
        System.out.println(B);

        // ===============================
        // Example 2: Qo'shish
        // ===============================
        CalculateMatrix C = new CalculateMatrix(A);
        C.sumMatrix(B);
        System.out.println("A + B:");
        System.out.println(C);

        // ===============================
        // Example 3: Ayirish
        // ===============================
        CalculateMatrix D = new CalculateMatrix(B);
        D.subMatrix(A);
        System.out.println("B - A:");
        System.out.println(D);

        // ===============================
        // Example 4: Skalyar bilan ko'paytirish
        // ===============================
        CalculateMatrix E = new CalculateMatrix(A);
        E.mulNumber(2.0);
        System.out.println("A * 2:");
        System.out.println(E);

        // ===============================
        // Example 5: Matritsa ko'paytmasi
        // ===============================
        CalculateMatrix F = new CalculateMatrix(2, 3);
        F.set(0, 0, 1); F.set(0, 1, 2); F.set(0, 2, 3);
        F.set(1, 0, 4); F.set(1, 1, 5); F.set(1, 2, 6);

        CalculateMatrix G = new CalculateMatrix(3, 2);
        G.set(0, 0, 7); G.set(0, 1, 8);
        G.set(1, 0, 9); G.set(1, 1, 10);
        G.set(2, 0, 11); G.set(2, 1, 12);

        F.mulMatrix(G);
        System.out.println("F * G:");
        System.out.println(F);

        // ===============================
        // Example 6: Transpozitsiya
        // ===============================
        CalculateMatrix H = F.transpose();
        System.out.println("Transpose of F*G:");
        System.out.println(H);

        // ===============================
        // Example 7: Determinant, Complements, Inverse
        // ===============================
        CalculateMatrix I = new CalculateMatrix(3, 3);
        I.set(0, 0, 4); I.set(0, 1, 7); I.set(0, 2, 2);
        I.set(1, 0, 3); I.set(1, 1, 6); I.set(1, 2, 1);
        I.set(2, 0, 2); I.set(2, 1, 5); I.set(2, 2, 3);

        System.out.println("Matrix I:");
        System.out.println(I);

        System.out.println("Determinant of I: " + I.determinant());

        CalculateMatrix complements = I.calcComplements();
        System.out.println("Algebraic complements of I:");
        System.out.println(complements);

        CalculateMatrix inverse = I.inverseMatrix();
        System.out.println("Inverse of I:");
        System.out.println(inverse);

        // ===============================
        // Example 8: eqMatrix
        // ===============================
        System.out.println("A equals B? " + A.eqMatrix(B));
        System.out.println("A equals A? " + A.eqMatrix(new CalculateMatrix(A)));
    }
}