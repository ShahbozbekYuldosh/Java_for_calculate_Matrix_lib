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
        if (rows_ != other.rows_ || cols_ != other.cols_) {
            throw new IllegalArgumentException("Matrices dimensions must match for addition");
        }

        for (int i = 0; i < rows_; i++) {
            for (int j = 0; j < cols_; j++) {
                matrix[i][j] += other.matrix[i][j];
            }
        }
    }

    public void subMatrix(CalculateMatrix other) {
        // TODO: Ayirish

        if (rows_ != other.rows_ || cols_ != other.cols_) {
            throw new IllegalArgumentException("Matrices dimensions must match for subtraction");
        }

        for (int i = 0; i < rows_; i++) {
            for (int j = 0; j < cols_; j++) {
                matrix[i][j] -= other.matrix[i][j];
            }
        }
    }

    public void mulNumber(int num) {
        // TODO: Skalyar bilan ko'paytirish

        for (int i = 0; i < rows_; i++) {
            for (int j = 0; j < cols_; j++) {
                matrix[i][j] *= num;
            }
        }
    }

    public void mulMatrix(CalculateMatrix other) {
        // TODO: Matritsa ko'paytmasi

        if (cols_ != other.rows_) {
            throw new IllegalArgumentException("Number of columns of first matrix must equal number of rows of second matrix");
        }

        int [][] result = new int[rows_][other.cols_];
        for (int i = 0; i < rows_; i++) {
            for (int j = 0; j < other.cols_; j++) {
                for (int k = 0; k < cols_; k++) {
                    result[i][j] += matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        this.cols_ = other.cols_;
        this.matrix = result;
    }

    public CalculateMatrix transpose() {
        // TODO: Transpozitsiya

        CalculateMatrix result = new CalculateMatrix(this.rows_, this.cols_);

        for (int i = 0; i < this.rows_; i++) {
            for (int j = 0; j < this.cols_; j++) {
                result.matrix[i][j] = this.matrix[i][j];
            }
        }
        return result;
    }

    public int determinant() {
        // TODO: Determinant

        if (rows_ != cols_) {
            throw new IllegalArgumentException("Matrix must be square to calculate determinant");
        }

        return calculateDeterminant(this.matrix);
    }

    private int calculateDeterminant(int[][] mat) {
        int n = mat.length;

        if (n == 1) return mat[0][0];
        if (n == 2) return mat[0][0] * mat[1][1] -  mat[0][1] * mat[1][0];

        int det = 0;

        for (int col = 0; col < n; col++) {
            int[][] subMatrix = new int[n-1][n-1];

            for (int i = 1; i < n; i++) {
                int subCol = 0;
                for (int j = 0; j < n; j++) {
                    if (j == col) continue;
                    subMatrix[i-1][subCol] = mat[i][j];
                    subCol++;
                }
            }
            det += Math.pow(-1, col) * mat[0][col] * calculateDeterminant(subMatrix);
        }
        return det;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows_; i++) {
            sb.append(Arrays.toString(matrix[i])).append("\n");
        }
        return sb.toString();
    }

}

