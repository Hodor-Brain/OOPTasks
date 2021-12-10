package Task8;

import java.io.File;
import java.util.List;

public class Main {
    private static final MatrixValidator matrixValidator = new MatrixValidator();

    public static void main(String[] args) {
        Matrix matrix = new Matrix(new File("src/main/java/Task8/matrix.txt"));
        Solver solver = new Solver(matrixValidator);
        List<Double> res = solver.solve(matrix);
        if(res!= null){
            System.out.println(res);
        }
    }
}
