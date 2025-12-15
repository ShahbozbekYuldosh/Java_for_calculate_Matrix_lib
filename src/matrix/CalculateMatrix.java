package matrix;

import java.util.Arrays;

public class CalculateMatrix {

    private int rows_;
    private int cols_;
    private double[][] matrix;

    // =======================
    // Constructors
    // =======================
    public CalculateMatrix() {
        this.rows_ = 2;
        this.cols_ = 2;
        this.matrix = new double[rows_][cols_];
    }

    public CalculateMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0)
            throw new IllegalArgumentException("Rows and Columns must be greater than 0");
        this.rows_ = rows;
        this.cols_ = cols;
        this.matrix = new double[rows][cols];
    }

    public CalculateMatrix(CalculateMatrix other) {
        this.rows_ = other.rows_;
        this.cols_ = other.cols_;
        this.matrix = new double[rows_][cols_];
        for (int i = 0; i < rows_; i++) {
            this.matrix[i] = Arrays.copyOf(other.matrix[i], cols_);
        }
    }

    // =======================
    // Getters and Setters
    // =======================
    public int getRows_() { return rows_; }

    public void setRows_(int rows) {
        if (rows <= 0) throw new IllegalArgumentException("Rows must be greater than 0");
        double[][] newMatrix = new double[rows][cols_];
        for (int i = 0; i < Math.min(rows, rows_); i++) {
            newMatrix[i] = Arrays.copyOf(matrix[i], cols_);
        }
        this.rows_ = rows;
        this.matrix = newMatrix;
    }

    public int getCols_() { return cols_; }

    public void setCols_(int cols) {
        if (cols <= 0) throw new IllegalArgumentException("Columns must be greater than 0");
        double[][] newMatrix = new double[rows_][cols];
        for (int i = 0; i < rows_; i++) {
            newMatrix[i] = Arrays.copyOf(matrix[i], cols);
        }
        this.cols_ = cols;
        this.matrix = newMatrix;
    }

    public double get(int i, int j) {
        if (i < 0 || i >= rows_ || j < 0 || j >= cols_)
            throw new IllegalArgumentException("Index out of bounds");
        return matrix[i][j];
    }

    public void set(int i, int j, double value) {
        if (i < 0 || i >= rows_ || j < 0 || j >= cols_)
            throw new IllegalArgumentException("Index out of bounds");
        matrix[i][j] = value;
    }

    // =======================
    // Matrix operations
    // =======================
    public boolean eqMatrix(CalculateMatrix other) {
        if (this.rows_ != other.rows_ || this.cols_ != other.cols_) return false;
        for (int i = 0; i < rows_; i++) {
            for (int j = 0; j < cols_; j++) {
                if (this.matrix[i][j] != other.matrix[i][j]) return false;
            }
        }
        return true;
    }

    public void sumMatrix(CalculateMatrix other) {
        if (rows_ != other.rows_ || cols_ != other.cols_)
            throw new IllegalArgumentException("Matrices dimensions must match for addition");
        for (int i = 0; i < rows_; i++)
            for (int j = 0; j < cols_; j++)
                matrix[i][j] += other.matrix[i][j];
    }

    public void subMatrix(CalculateMatrix other) {
        if (rows_ != other.rows_ || cols_ != other.cols_)
            throw new IllegalArgumentException("Matrices dimensions must match for subtraction");
        for (int i = 0; i < rows_; i++)
            for (int j = 0; j < cols_; j++)
                matrix[i][j] -= other.matrix[i][j];
    }

    public void mulNumber(double num) {
        for (int i = 0; i < rows_; i++)
            for (int j = 0; j < cols_; j++)
                matrix[i][j] *= num;
    }

    public void mulMatrix(CalculateMatrix other) {
        if (cols_ != other.rows_)
            throw new IllegalArgumentException("Number of columns of first matrix must equal number of rows of second matrix");

        double[][] result = new double[rows_][other.cols_];
        for (int i = 0; i < rows_; i++)
            for (int j = 0; j < other.cols_; j++)
                for (int k = 0; k < cols_; k++)
                    result[i][j] += matrix[i][k] * other.matrix[k][j];

        this.cols_ = other.cols_;
        this.matrix = result;
    }

    public CalculateMatrix transpose() {
        CalculateMatrix result = new CalculateMatrix(cols_, rows_);
        for (int i = 0; i < rows_; i++)
            for (int j = 0; j < cols_; j++)
                result.matrix[j][i] = matrix[i][j];
        return result;
    }

    public double determinant() {
        if (rows_ != cols_) throw new IllegalArgumentException("Matrix must be square to calculate determinant");
        return calculateDeterminant(matrix);
    }

    private double calculateDeterminant(double[][] mat) {
        int n = mat.length;
        if (n == 1) return mat[0][0];
        if (n == 2) return mat[0][0]*mat[1][1] - mat[0][1]*mat[1][0];

        double det = 0.0;
        for (int col = 0; col < n; col++) {
            double[][] subMatrix = new double[n-1][n-1];
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
        if (rows_ != cols_) throw new IllegalArgumentException("Matrix must be square to calculate complements");

        CalculateMatrix result = new CalculateMatrix(rows_, cols_);
        for (int i = 0; i < rows_; i++) {
            for (int j = 0; j < cols_; j++) {
                double[][] subMatrix = new double[rows_-1][cols_-1];
                int r = 0;
                for (int row = 0; row < rows_; row++) {
                    if (row == i) continue;
                    int c = 0;
                    for (int col = 0; col < cols_; col++) {
                        if (col == j) continue;
                        subMatrix[r][c] = matrix[row][col];
                        c++;
                    }
                    r++;
                }
                result.matrix[i][j] = Math.pow(-1, i+j) * calculateDeterminant(subMatrix);
            }
        }
        return result;
    }

    public CalculateMatrix inverseMatrix() {
        double det = this.determinant();
        if (det == 0) throw new ArithmeticException("Matrix determinant is zero, inverse does not exist");

        CalculateMatrix complements = this.calcComplements();
        CalculateMatrix adjugate = complements.transpose();
        adjugate.mulNumber(1.0 / det);

        return adjugate;
    }

    // =======================
    // toString
    // =======================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows_; i++) {
            sb.append(Arrays.toString(matrix[i])).append("\n");
        }
        return sb.toString();
    }
}