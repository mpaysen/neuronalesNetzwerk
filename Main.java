import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //create neuralNetwork obj with three layers
        neuralNetwork n = new neuralNetwork(784,100,10,0.3);
        //trainloop(n, 10, "mnist_train.csv", 255); // train neuralNetwork "n", outputnodes and with CSV and scale.
        //save("test_wih.bin", n.getwih()); //save trained weight input -> hidden
        //save("test_who.bin", n.getwho()); //save trained weight hidden -> output
        loadwho(n, "test_who.bin"); //load trained weight hidden -> output
        loadwih(n, "test_wih.bin"); //load trained weight input -> hidden
        System.out.println(query(n, 1001, "mnist_test.csv", 255)); //query data from neuralNetwork "n", index from CSV and scale.
        

   }

   public static String query(neuralNetwork n, int index, String path, int scale) throws Exception {
        List<List<Double>> data_list = new ArrayList<List<Double>>(); 
        data_list = readData(path); //read data into ArrayList "data_list"
        List<Double> all_values = new ArrayList<>();
        all_values = data_list.get(index); //read data into ArrayList "all_values" by index row from ArrayList "data_list"
        List<Double> inputs = new ArrayList<>();
        inputs = scale(all_values, scale); //read data into ArrayList "inputs" from ArrayList "all_values" and scale it correct also remove index 0 (correct answer).
        List<List<Double>> I = new ArrayList<List<Double>>();
        I.add(inputs); //create two dimensional Arraylist from ArrayList "inputs"
        double[][] result = n.query(I); //query data from neuralNetwork "n"
        double max = 0.0;
        int number = -1;
        for (int i = 0; i < result[0].length; ++i) { // create number from results (maximum)
            if (result[0][i] > max) {
                max = result[0][i];
                number = i;
            }
        }
        //String strA = Arrays.deepToString(result);
        String str = "Erwartete Zahl: " + all_values.get(0) + "\n";
        str += "Antwort des Neuronalen Netzwerks: " + number;
        return str;
   }


//save weight
   public static void save(String path, double[][] board) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) { 
        out.writeObject(board); 
      } 
   }

//load trained weight hidden -> output
   public static void loadwho(neuralNetwork n, String path) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) { 
        n.setwho((double[][]) in.readObject());
      }
   }
//load trained weight input -> hidden
   public static void loadwih(neuralNetwork n, String path) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) { 
        n.setwih((double[][]) in.readObject());
      }
   }

//train neuronalNetwork
   public static void trainloop(neuralNetwork n, int outputnodes, String path, int scale) throws Exception {
    List<List<Double>> data_list = new ArrayList<List<Double>>();
    data_list = readData(path); //read data into c

    for (int i = 0; i < data_list.size(); ++i) { // for loop for every row in CSV (ArrayList "data_list")
        List<Double> all_values = new ArrayList<>();
        all_values = data_list.get(i); //read data into ArrayList "all_values" by index "i" row from ArrayList "data_list"

        List<Double> inputs = new ArrayList<>();
        inputs = scale(all_values, scale); //read data into ArrayList "inputs" from ArrayList "all_values" and scale it correct also remove index 0 (correct answer).

        List<Double> targets = new ArrayList<>(); //create new ArrayList "tragets" with min. 0.01 at every array index.
            for (int f = 0; f < outputnodes; ++f) {
            targets.add(0.01);
            }
        double d = all_values.get(0); //get correct answer from ArrayList "all_values" by index 0
        targets.set((int)d, 0.99);  //set ArrayList "tragets" to 0.99 on index "d" (correct answer).

        List<List<Double>> T = new ArrayList<List<Double>>(); //create two dimensional Arraylist from ArrayList "targets"
        T.add(targets);

        List<List<Double>> I = new ArrayList<List<Double>>(); //create two dimensional Arraylist from ArrayList "inputs"
        I.add(inputs);
        // train with data.
        n.train(I, T);
    }
   }
//scale the data and remove index 0;
    public static List<Double> scale(List<Double> m, int scale) {
        List<Double> scaled = new ArrayList<>();
        List<Double> input = m;
        for (int i = 0; i < input.size(); ++i) {
            scaled.add(i, (input.get(i) / scale * 0.99) + 0.01);
        }
        return scaled.subList(1, scaled.size());
    }


//read data from CSV, split by ','
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


