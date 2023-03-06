import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Method5 {
    ArrayList<Double> a = new ArrayList<Double>();
    ArrayList<Double> b = new ArrayList<Double>();
    ArrayList<Double> fb = new ArrayList<Double>();
    ArrayList<Double> ab = new ArrayList<Double>();

    public void method5(double sa, double sb, double eps, int n, int save) {
        if (Math.abs(Function.getDerivativeMaxValue(sa, sb, n)) > 1) {
            System.out.println("Метод простой итерации не сходиться");
            return;
        }

        if (Function.getFunction(sa, n) * Function.getDerivativeFunction2(sa, n) > 0) {
            a.add(sa);
        } else {
            a.add(sb);
        }

        do {
            b.add(Function.getFiFunction(a.get(a.size() - 1), sa, sb, n));
            fb.add(Function.getFunction(b.get(b.size() - 1), n));
            ab.add(Math.abs(a.get(a.size() - 1) - b.get(b.size() - 1)));
            a.add(b.get(b.size() - 1));
        } while (ab.get(ab.size() - 1) > eps);

        b.add(Function.getFiFunction(a.get(a.size() - 1), sa, sb, n));
        fb.add(Function.getFunction(b.get(b.size() - 1), n));
        ab.add(Math.abs(a.get(a.size() - 1) - b.get(b.size() - 1)));

        if (save == 1) {
            outputConsole();
        } else {
            outputFile(n);
        }
    }

    public void outputConsole() {
        System.out.println("+------------------------------------------------" +
                "-------------------------------+");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", " xk", " xk+1", " f(xk+1)", " |xk+1 - xk| ");
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+");

        for (int i = 0; i < a.size(); i++) {
            System.out.printf("|%-15d|%-15.4f|%-15.4f|%-15.4f|%-15.4f|\n",
                    i, a.get(i), b.get(i), fb.get(i), ab.get(i));
            System.out.println("+---------------+---------------+---------------+" +
                    "---------------+---------------+");
        }
    }

    public void outputFile(int n) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/method5-" + n + ".txt");
            fileWriter.write("+------------------------------------------------" +
                    "-------------------------------+\n");
            fileWriter.write(String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", " xk", " xk+1", " f(xk+1)", " |xk+1 - xk| "));
            fileWriter.write("+---------------+---------------+---------------+" +
                    "---------------+---------------+\n");
            for (int i = 0; i < a.size(); i++) {
                fileWriter.write(String.format("|%-15d|%-15.4f|%-15.4f|%-15.4f|%-15.4f|\n",
                        i, a.get(i), b.get(i), fb.get(i), ab.get(i)));
                fileWriter.write("+---------------+---------------+---------------+" +
                        "---------------+---------------+\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось сохранить");
        }
    }
}
