import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int numOfFunction, numOfMethod, input, save;
            double a, b, eps;

            System.out.println("Это вторая лабораторная работа по Вычислительной математике");
            System.out.println("""
                    Введите:
                    0 - для выхода
                    1 - для ввода данных из консоли
                    2 - для ввода данных из файла""");

            input = inputInt(in);

            switch (input) {
                case (1):
                    while (true) {
                        System.out.println("""
                                Введите номер метода:
                                1 - Метод половинного деления
                                3 - Метод Ньютона
                                5 - Метод простой итерации
                                6 - Метод Ньютона для решение систем нелинейных уравнений""");
                        numOfMethod = inputInt(in);
                        if (!(numOfMethod == 1 || numOfMethod == 3 || numOfMethod == 5 || numOfMethod == 6)) {
                            System.out.println("Некорректный ввод");
                            continue;
                        }
                        break;
                    }

                    if (numOfMethod != 6) {
                        while (true) {
                            System.out.println("""
                                    Введите номер функции:
                                    1 - -x^3 + 5.67 * x^2 - 7.12 * x + 1.34
                                    2 - x^3 + 3 * x^2 + 3 * x + 1
                                    3 - cos(x)
                                    4 - x^3 - x + 4""");
                            numOfFunction = inputInt(in);
                            if (numOfFunction < 0 || numOfFunction > 4) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        while (true) {
                            System.out.println("Введите a:");
                            a = inputDouble(in);
                            if (a == Double.MIN_VALUE) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        while (true) {
                            System.out.println("Введите b:");
                            b = inputDouble(in);
                            if (b == Double.MIN_VALUE) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        if (a >= b) {
                            System.out.println("Введите правильные границы а и b (a < b)");
                            continue;
                        }

                        int tmp = Function.checkRoots(a, b, numOfFunction);
                        if (tmp != 1) {
                            System.out.println("На данном интервале не один корень(" + tmp + ")");
                            continue;
                        }

                    } else {
                        while (true) {
                            System.out.println("""
                                    Введите номер функции:
                                    1 - |x^2 + y^2 = 4
                                        |y = 3 * x^2
                                    2 - |x^2 + y^2 = 1
                                        |y = x""");
                            numOfFunction = inputInt(in);
                            if (numOfFunction < 0 || numOfFunction > 2) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        while (true) {
                            System.out.println("Введите x0:");
                            a = inputDouble(in);
                            if (a == Double.MIN_VALUE) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        while (true) {
                            System.out.println("Введите y0:");
                            b = inputDouble(in);
                            if (b == Double.MIN_VALUE) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("Введите погрешность [0,0001; 1]:");
                        eps = inputDouble(in);
                        if (eps < 0.0001 || eps > 1) {
                            System.out.println("Некорректный ввод");
                            continue;
                        }
                        break;
                    }

                    while (true) {
                        System.out.println("0 - сохранить в файл\n" +
                                "1 - вывести ответ в консоль");
                        save = inputInt(in);
                        if (!(save == 1 || save == 0)) {
                            System.out.println("Некорректный ввод");
                            continue;
                        }
                        break;
                    }

                    Chart chart = new Chart();

                    switch (numOfMethod) {
                        case (1) -> {
                            Method1 method1 = new Method1();
                            method1.method1(a, b, eps, numOfFunction, save);
                            chart.drawFunction(a, b, numOfFunction, numOfMethod);
                        }
                        case (3) -> {
                            Method3 method3 = new Method3();
                            method3.method3(a, b, eps, numOfFunction, save);
                            chart.drawFunction(a, b, numOfFunction, numOfMethod);
                        }
                        case (5) -> {
                            Method5 method5 = new Method5();
                            method5.method5(a, b, eps, numOfFunction, save);
                            chart.drawFunction(a, b, numOfFunction, 5);
                        }
                        case (6) -> {
                            Method6 method6 = new Method6();
                            method6.method6(a, b, eps, numOfFunction, save);
                            chart.drawTwoFunction(numOfFunction);
                        }
                    }

                    break;
                case (2):
                    break;
                case (0):
                    System.out.println("Конец работы");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    public static int inputInt(Scanner in) {
        int num;
        try {
            num = in.nextInt();
        } catch (Exception e) {
            if (!in.hasNextLine()) {
                System.out.println("Конец работы");
                System.exit(0);
            } else {
                System.out.println("Плохая строка");
            }
            in.nextLine();
            return -1;
        }
        return num;
    }

    public static double inputDouble(Scanner in) {
        double num;
        try {
            num = in.nextDouble();
        } catch (Exception e) {
            if (!in.hasNextLine()) {
                System.out.println("Конец работы");
                System.exit(0);
            } else {
                System.out.println("Плохая строка");
            }
            in.nextLine();
            return Double.MIN_VALUE;
        }
        return num;
    }
}