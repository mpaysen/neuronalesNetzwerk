import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        neuralNetwork n = new neuralNetwork(784,100,10,0.3);
        //trainloop(n, 10, "mnist_train.csv", 255);
        //save("test_wih.txt", n.getwih());
        //save("test_who.txt", n.getwho());
        loadwho(n, "test_who.txt");
        loadwih(n, "test_wih.txt");
        System.out.println(query(n, 1, "mnist_test.csv", 255));
        

   }

   public static String query(neuralNetwork n, int index, String path, int scale) throws Exception {
        List<List<Double>> data_list = new ArrayList<List<Double>>();
        data_list = readData(path);
        List<Double> all_values = new ArrayList<>();
        all_values = data_list.get(index);
        List<Double> inputs = new ArrayList<>();
        inputs = scale(all_values, scale);
        List<List<Double>> I = new ArrayList<List<Double>>();
        I.add(inputs);
        double[][] result = n.query(I);
        double max = 0.0;
        int number = -1;
        for (int i = 0; i < result[0].length; ++i) {
            if (result[0][i] > max) {
                max = result[0][i];
                number = i;
            }
        }
        String str = "Erwartete Zahl: " + all_values.get(0) + "\n";
        str += "Antwort des Neuronalen Netzwerks: " + number;
        return str;
   }



   public static void save(String path, double[][] board) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) { 
        out.writeObject(board); 
      } 
   }

   public static void loadwho(neuralNetwork n, String path) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) { 
        n.setwho((double[][]) in.readObject());
      }
   }

   public static void loadwih(neuralNetwork n, String path) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) { 
        n.setwih((double[][]) in.readObject());
      }
   }

   public static void trainloop(neuralNetwork n, int outputnodes, String path, int scale) throws Exception {
    List<List<Double>> data_list = new ArrayList<List<Double>>();
    data_list = readData(path);

    for (int i = 0; i < data_list.size(); ++i) {
        List<Double> all_values = new ArrayList<>();
        all_values = data_list.get(i);

        List<Double> inputs = new ArrayList<>();
        inputs = scale(all_values, scale);

        List<Double> targets = new ArrayList<>();
            for (int f = 0; f < outputnodes; ++f) {
            targets.add(0.01);
            }
        double d = all_values.get(0);
        targets.set((int)d, 0.99);

        List<List<Double>> T = new ArrayList<List<Double>>();
        T.add(targets);

        List<List<Double>> I = new ArrayList<List<Double>>();
        I.add(inputs);
        n.train(I, T);
    }
   }

    public static List<Double> scale(List<Double> m, int scale) {
        List<Double> scaled = new ArrayList<>();
        List<Double> input = m;
        for (int i = 0; i < input.size(); ++i) {
            scaled.add(i, (input.get(i) / scale * 0.99) + 0.01);
        }
        return scaled.subList(1, scaled.size());
    }

    public static List<List<Double>> readData(String path) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        try {
            String row;
            List<List<Double>> input = new ArrayList<List<Double>>();
            while ((row = csvReader.readLine()) != null) {
                String[] data = (row.split(","));
                List<Double> dataInt = new ArrayList<>();
                for(int i = 0; i < data.length; ++i){
                    dataInt.add(Double.parseDouble(data[i]));
                }
                    input.add(dataInt);
            } 
            csvReader.close();
            return input;
        } catch (Exception e) {
            throw e;
        }
    }
}


