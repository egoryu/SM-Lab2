import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Method6 {
    ArrayList<Double> a = new ArrayList<Double>();
    ArrayList<Double> b = new ArrayList<Double>();

    ArrayList<Double> da = new ArrayList<Double>();
    ArrayList<Double> db = new ArrayList<Double>();
    ArrayList<Double> fa = new ArrayList<Double>();
    ArrayList<Double> fb = new ArrayList<Double>();

    public void method6(double sa, double sb, double eps, int n, int save) {
        a.add(sa);
        b.add(sb);

        if (n == 1) {
            do {
                db.add((12.0 - 3 * Math.pow(b.get(b.size() - 1), 2) - b.get(b.size() - 1)) / (6 * b.get(b.size() - 1) + 1));
                da.add((db.get(db.size() - 1) + b.get(b.size() - 1) - 3 * Math.pow(a.get(a.size() - 1), 2)) /
                        (6 * a.get(a.size() - 1)));
                a.add(a.get(a.size() - 1) + da.get(da.size() - 1));
                b.add(b.get(b.size() - 1) + db.get(db.size() - 1));
                fa.add(Math.pow(a.get(a.size() - 1), 2) + Math.pow(b.get(b.size() - 1), 2) - 4);
                fb.add(3 * Math.pow(a.get(a.size() - 1), 2) - b.get(b.size() - 1));
            } while (Math.max(Math.abs(da.get(da.size() - 1)), Math.abs(db.get(db.size() - 1))) > eps);
        } else {
            do {
                db.add((1 - a.get(a.size() - 1) * b.get(b.size() - 1) - Math.pow(b.get(b.size() - 1), 2)) /
                        (2 * b.get(b.size() - 1) + 2 * a.get(a.size() - 1)));
                da.add(b.get(b.size() - 1) + db.get(db.size() - 1) - a.get(a.size() - 1));
                a.add(a.get(a.size() - 1) + da.get(da.size() - 1));
                b.add(b.get(b.size() - 1) + db.get(db.size() - 1));
                fa.add(Math.pow(a.get(a.size() - 1), 2) + Math.pow(b.get(b.size() - 1), 2) - 1);
                fb.add(a.get(a.size() - 1) - b.get(b.size() - 1));
            } while (Math.max(Math.abs(da.get(da.size() - 1)), Math.abs(db.get(db.size() - 1))) > eps);
        }

        if (save == 1) {
            outputConsole();
        } else {
            outputFile(n);
        }
    }

    public void outputConsole() {
        System.out.println("+-------------------------------------------------" +
                "--------------------------------------------------------------+");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", "x", "y", "dx", "dy", "f(x+dx,y+dy)", "g(x+dx,y+dy)");
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+");

        for (int i = 0; i < fa.size(); i++) {
            System.out.printf("|%-15d|%-15.4f|%-15.4f|%-15.4f|%-15.4f|%-15.4f|%-15.4f|\n",
                    i, a.get(i), b.get(i), da.get(i), db.get(i), fa.get(i), fb.get(i));
            System.out.println("|---------------+---------------+---------------+" +
                    "---------------+---------------+---------------+---------------|");
        }
    }

    public void outputFile(int n) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/method6-" + n + ".txt");
            fileWriter.write("+-------------------------------------------------" +
                    "--------------------------------------------------------------+\n");
            fileWriter.write(String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", "x", "y", "dx", "dy", "f(x+dx,y+dy)", "g(x+dx,y+dy)"));
            fileWriter.write("+---------------+---------------+---------------+" +
                    "---------------+---------------+---------------+---------------+\n");
            for (int i = 0; i < fa.size(); i++) {
                fileWriter.write(String.format("|%-15d|%-15.4f|%-15.4f|%-15.4f|%-15.4f|%-15.4f|%-15.4f|\n",
                        i, a.get(i), b.get(i), da.get(i), db.get(i), fa.get(i), fb.get(i)));
                fileWriter.write("|---------------+---------------+---------------+" +
                        "---------------+---------------+---------------+---------------|\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось сохранить");
        }
    }
}
