import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Method3 {
    ArrayList<Double> a = new ArrayList<Double>();
    ArrayList<Double> b = new ArrayList<Double>();
    ArrayList<Double> fa = new ArrayList<Double>();
    ArrayList<Double> fa1 = new ArrayList<Double>();
    ArrayList<Double> ab = new ArrayList<Double>();

    public void method3(double sa, double sb, double eps, int n, int save) {
        if (Function.getFunction(sa, n) * Function.getDerivativeFunction2(sa, n) > 0) {
            a.add(sa);
        } else  {
            a.add(sb);
        }

        do {
            fa.add(Function.getFunction(a.get(a.size() - 1), n));
            fa1.add(Function.getDerivativeFunction1(a.get(a.size() - 1), n));
            b.add(a.get(a.size() - 1) - fa.get(fa.size() - 1) / fa1.get(fa1.size() - 1));
            ab.add(Math.abs(a.get(a.size() - 1) - b.get(b.size() - 1)));
            a.add(b.get(b.size() - 1));
        } while (ab.get(ab.size() - 1) > eps);

        fa.add(Function.getFunction(a.get(a.size() - 1), n));
        fa1.add(Function.getDerivativeFunction1(a.get(a.size() - 1), n));
        b.add(a.get(a.size() - 1) - fa.get(fa.size() - 1) / fa1.get(fa1.size() - 1));
        ab.add(Math.abs(a.get(a.size() - 1) - b.get(b.size() - 1)));

        if (save == 1) {
            outputConsole();
        } else {
            outputFile(n);
        }
    }

    public void outputConsole() {
        System.out.println("+------------------------------------------------" +
                "-----------------------------------------------+\n");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", " xk", " f(xk)", " f`(xk)", " xk+1", " |xk+1 - xk| ");
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+\n");

        for (int i = 0; i < a.size(); i++) {
            System.out.printf("|%-15d|%-15.4f|%-15.4f|%-15.4f|%-15.4f|%-15.4f|\n",
                    i, a.get(i), fa.get(i), fa1.get(i), b.get(i), ab.get(i));
            System.out.println("+---------------+---------------+---------------+" +
                    "---------------+---------------+---------------+\n");
        }
    }

    public void outputFile(int n) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/method3-" + n + ".txt");
            fileWriter.write("+------------------------------------------------" +
                    "-----------------------------------------------+");
            fileWriter.write(String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", " xk", " f(xk)", " f`(xk)", " xk+1", " |xk+1 - xk| "));
            fileWriter.write("+---------------+---------------+---------------+" +
                    "---------------+---------------+---------------+");
            for (int i = 0; i < fa.size(); i++) {
                fileWriter.write(String.format("|%-15d|%-15.4f|%-15.4f|%-15.4f|%-15.4f|%-15.4f|\n",
                        i, a.get(i), fa.get(i), fa1.get(i), b.get(i), ab.get(i)));
                fileWriter.write("+---------------+---------------+---------------+" +
                        "---------------+---------------+---------------+");
            }
        } catch (IOException e) {
            System.out.println("Не удалось сохранить");
        }
    }
}
