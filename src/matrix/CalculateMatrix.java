package matrix;

import java.util.Arrays;

public class CalculateMatrix {

    private int rows_;
    private int cols_;
    private int[][] matrix;

    public CalculateMatrix() {
        this.rows_ = 2;
        this.cols_ = 2;
        this.matrix = new int[rows_][cols_];
    }

    public CalculateMatrix(int rows_, int cols_) {
        if (rows_ <= 0 || cols_ <= 0) {
            throw new IllegalArgumentException("Rows and Columns must be greater than 0");
        }
        this.rows_ = rows_;
        this.cols_ = cols_;
        this.matrix = new int[rows_][cols_];
    }

    public CalculateMatrix(CalculateMatrix other) {
        this.rows_ = other.rows_;
        this.cols_ = other.cols_;
        this.matrix = new int[rows_][cols_];
        for (int i = 0; i < rows_; i++) {
            this.matrix[i] = Arrays.copyOf(other.matrix[i], cols_);
        }
    }

    public int getRows_() {
        return rows_;
    }

    public void setRows_(int rows) {
        if (rows <= 0) throw new IllegalArgumentException("Rows must be greater than 0");
        int[][] newMatrix = new int[rows][cols_];
        for (int i = 0; i < Math.min(rows, rows_); i++) {
            newMatrix[i] = Arrays.copyOf(this.matrix[i], cols_);
        }
        this.rows_ = rows;
        this.matrix = newMatrix;
    }

    public int getCols_() {
        return cols_;
    }

    public void setCols_(int cols) {
        if (cols <= 0) throw new IllegalArgumentException("Column must be greater than 0");
        int[][] newMatrix = new int[rows_][cols];
        for (int i = 0; i < rows_; i++) {
            newMatrix[i] = Arrays.copyOf(this.matrix[i], cols_);
        }
        this.cols_ = cols;
        this.matrix = newMatrix;
    }
//--------------------------------------------------------------------------------------------------

    public int get(int i, int j) {
        if (i < 0 || i >= rows_ || j < 0 || j >= cols_) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return matrix[i][j];
    }

    public void set(int i, int j, int value) {
        if (i < 0 || i >= rows_ || j < 0 || j >= cols_) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        matrix[i][j] = value;
    }
//--------------------------------------------------------------------------------------------------

    public boolean eqMatrix(CalculateMatrix other) {
//        TODO: Tenglikni tekshirish kerak!
        return false;
    }

    public void sumMatrix(CalculateMatrix other) {
        // TODO: Qo'shish
    }

    public void subMatrix(CalculateMatrix other) {
        // TODO: Ayirish
    }

    public void mulNumber(int num) {
        // TODO: Skalyar bilan ko'paytirish
    }

    public void mulMatrix(CalculateMatrix other) {
        // TODO: Matritsa ko'paytmasi
    }

    public CalculateMatrix transpose() {
        // TODO: Transpozitsiya
        return null;
    }

    public int determinant() {
        // TODO: Determinant
        return 0;
    }

    public CalculateMatrix calcComplements() {
        // TODO: Algebraik komplementlar
        return null;
    }

    public CalculateMatrix inverseMatrix() {
      //  TODO: Inversiya
        return null;
    }

//--------------------------------------------------------------------------------------------------



}

