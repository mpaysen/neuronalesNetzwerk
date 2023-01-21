public class numja {

  public  static double[][] random(double[][] m) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = Math.random();
      }
    }
    return m;
  }

  public  static double[][] random(int r, int c) {
    double[][] m = new double[r][c];
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = Math.random();
      }
    }
    return m;
  }

  public static double[][] add(double[][] m, double s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] += s;
      }
    }
    return m;
  }

  public static double[][] sub(double[][] m, double s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] -= s;
      }
    }
    return m;
  }

  public static double[][] sub(double s, double[][] m) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = s - m[i][f];
      }
    }
    return m;
  }

  public static double[][] add(double[][] m, double[][] s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] += s[i][f];
      }
    }
    return m;
  }

  public static double[] add(double[] m,double s) {
    for(int i = 0; i < m.length; ++i){
        m[i] += s;
    }
    return m;
  }

  public static double[][] sub(double[][] m, double[][] s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] -= s[i][f];
      }
    }
    return m;
  }

  public static double[][] dot( double[][] m2, double[][] m1) {
    //Zeilenlänge (von m1) und Spaltenlänge (von m2) müssen identisch sein
    if (m1 == null | m2 == null | m1[0].length != m2.length) {
      throw new IllegalArgumentException();
    }
    double[][] m3 = new double[m1.length][m2[0].length];
    for (int i = 0; i < m1.length; i++) {
      for (int p = 0; p < m2[0].length; p++) {
        double k = 0;
        for (int n = 0; n < m1[i].length; n++) {
          k += m1[i][n] * m2[n][p];
        }
        m3[i][p] = k;
      }
    }
    return m3;
  }

  public static double[][] transpose(double[][] m1) {
    double[][] m = new double[m1[0].length][m1.length];
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = m1[f][i];
      }
    }
    return m;
  }

  public static double[][] dot(double[][] m, double s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] *= s;
      }
    }
    return m;
  }

  public static double[][] dots(double[][] m, double[][] s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] *= s[i][f];
      }
    }
    return m;
  }

private static double sigmoid(double t) {
    return 1 / (1 + Math.pow(Math.E, (-1 * t)));
}

public static double[][] sigmoid(double[][] x, boolean deriv) {
    double[][] result = new double[x.length][x[0].length];

    for (int i = 0; i < x.length; i++) {
        for (int j = 0; j < x[i].length; j++) {
            double sigmoidCell = sigmoid(x[i][j]);

            if (deriv == true) {
                result[i][j] = sigmoidCell * (1 - sigmoidCell);
            } else {
                result[i][j] = sigmoidCell;
            }
        }
    }

    return result;
}

}
