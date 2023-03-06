public class Function {
    public static double getFunction(double x, int n) {
        if (n == 1) {
            return -Math.pow(x, 3) + 5.67 * Math.pow(x, 2) - 7.12 * x + 1.34;
        } else if (n == 2) {
            return Math.pow(x, 3) + 3 * Math.pow(x, 2) + 3 * x + 1;
        } else if (n == 3) {
            return Math.cos(x);
        } else {
            return Math.pow(x, 3) - x + 4;
        }
    }

    public static double getFiFunction(double x, double a, double b, int n) {
        double lambda = -1.0 / getMaxValue(a, b, n);

        return x + lambda * getFunction(x, n);
    }

    public static double getDerivativeFunction1(double x, int n) {
        if (n == 1) {
            return -3 * Math.pow(x, 2) + 2 * 5.67 * x - 7.12;
        } else if (n == 2) {
            return 3 * Math.pow(x, 2) + 6 * x + 3;
        } else if (n == 3) {
            return -Math.sin(x);
        } else {
            return 3 * Math.pow(x, 2) - 1;
        }
    }

    public static double getFiDerivativeFunction1(double x, double a, double b, int n) {
        double lambda = -1.0 / getMaxValue(a, b, n);

        return 1 + lambda * getDerivativeFunction1(x, n);
    }
    public static double getDerivativeFunction2(double x, int n) {
        if (n == 1) {
            return -6 * x + 2 * 5.67;
        } else if (n == 2) {
            return 6 * x + 6;
        } else if (n == 3) {
            return -Math.cos(x);
        } else {
            return 6 * x;
        }
    }

    public static int checkRoots(double a, double b, int n) {
        int ans = 0;

        for (double i = a; i <= b - 0.1; i += 0.1) {
            double tmp = getFunction(i, n) * getFunction(i + 0.09, n);
            if (tmp < 0.0) {
                ans++;
            }
        }

        return ans;
    }

    private static double getMaxValue(double a, double b, int n) {
        double ans = 0;

        for (double i = a; i <= b; i += 0.1) {
            ans = Math.max(Math.abs(ans), Math.abs(getDerivativeFunction1(i, n)));
        }

        return ans;
    }

    public static double getDerivativeMaxValue(double a, double b, int n) {
        double ans = 0;

        for (double i = a; i <= b; i += 0.1) {
            ans = Math.max(Math.abs(ans), Math.abs(getFiDerivativeFunction1(i, a, b, n)));
        }

        return ans;
    }
}
