public class numja {

  //fill two dimensional array "m" with random numbers
  public static double[][] random(double[][] m) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = Math.random();
      }
    }
    return m;
  }
  //create an two dimensional array "m" with rows "r" and colums "c" filled with random numbers
  public static double[][] random(int r, int c) {
    double[][] m = new double[r][c];
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = Math.random();
      }
    }
    return m;
  }
 //add skalar "s" to  two dimensional array "m"
  public static double[][] add(double[][] m, double s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] += s;
      }
    }
    return m;
  }
//sub skalar "s" from two dimensional array "m"
  public static double[][] sub(double[][] m, double s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] -= s;
      }
    }
    return m;
  }
//sub two dimensional array "m" from skalar "s"
  public static double[][] sub(double s, double[][] m) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = s - m[i][f];
      }
    }
    return m;
  }
//add every double from two dimensional array "s" to "m", arrays musst be identical
  public static double[][] add(double[][] m, double[][] s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] += s[i][f];
      }
    }
    return m;
  }
 //add skalar "s" to  two array "m"
  public static double[] add(double[] m,double s) {
    for(int i = 0; i < m.length; ++i){
        m[i] += s;
    }
    return m;
  }
//sub every double from two dimensional array "s" from "m", arrays musst be identical
  public static double[][] sub(double[][] m, double[][] s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] -= s[i][f];
      }
    }
    return m;
  }

//matrix multiplication with two two dimensional arrays "m2" and "m1"
  public static double[][] dot( double[][] m2, double[][] m1) {
    ///line length (of m1) and column length (of m2) must be identical
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
//transpose matrix m1
  public static double[][] transpose(double[][] m1) {
    double[][] m = new double[m1[0].length][m1.length];
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] = m1[f][i];
      }
    }
    return m;
  }
//skalar multiplication with "s" and two dimensional array "m"
  public static double[][] dot(double[][] m, double s) {
    for(int i = 0; i < m.length; ++i){
      for (int f = 0; f < m[i].length; ++f) {
        m[i][f] *= s;
      }
    }
    return m;
  }
//skalar multiplication with two dimensional array "s" and two dimensional array "m"
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

//sigmoid functin on two dimensional array "x"
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
