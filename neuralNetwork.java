import java.util.List;

public class neuralNetwork {
  private int inputnodes;
  private int hiddennodes;
  private int outputnodes;
  private double learningrate;
  private double[][] wih;
  private double[][] who;

  public neuralNetwork() {
    this(3, 3, 3, 0.3);
  }

  public neuralNetwork(int inputnodes, int hiddennodes, int outputnodes, double learningrate) {  
    this.inputnodes = inputnodes;
    this.hiddennodes = hiddennodes;
    this.outputnodes = outputnodes;
    this.learningrate = learningrate;
    this.wih = numja.sub(numja.random( this.inputnodes, this.hiddennodes), 0.5);
    this.who = numja.sub(numja.random( this.hiddennodes, this.outputnodes), 0.5);
  }


  public void train(List<List<Double>> input_list, List<List<Double>> targets_list) {
    double[][] inputs = input_list.stream().map(l -> l.stream().mapToDouble(Double::doubleValue).toArray()).toArray(double[][]::new);
    double[][] targets = targets_list.stream().map(l -> l.stream().mapToDouble(Double::doubleValue).toArray()).toArray(double[][]::new);

    double[][] hidden_inputs = numja.dot(this.wih, inputs);
    double[][] hidden_outputs = numja.sigmoid(hidden_inputs, false);

    double[][] final_inputs = numja.dot(this.who, hidden_outputs);
    double[][] final_outputs = numja.sigmoid(final_inputs, false);

    double[][] output_errors = numja.sub(targets, final_outputs);
    double[][] hidden_errors = numja.dot(numja.transpose(this.who), output_errors);

    this.who = numja.add(this.who, numja.dot(numja.dot(numja.dots( numja.dots(output_errors, final_outputs), numja.sub(1.0, final_outputs)), numja.transpose(hidden_outputs)), learningrate));

    this.wih = numja.add(this.wih, numja.dot(numja.dot(numja.dots( numja.dots(hidden_errors, hidden_outputs), numja.sub(1.0, hidden_outputs)), numja.transpose(inputs)), learningrate));
  }

  public double[][] query(List<List<Double>> input_list) {

    double[][] inputs = input_list.stream().map(l -> l.stream().mapToDouble(Double::doubleValue).toArray()).toArray(double[][]::new);

    double[][] hidden_inputs = numja.dot(this.wih, inputs);
    
    double[][] hidden_outputs = numja.sigmoid(hidden_inputs, false);

    double[][] final_inputs = numja.dot(this.who, hidden_outputs);
    double[][] final_outputs = numja.sigmoid(final_inputs, false);

    return final_outputs;
  }

  public double[][] getwih(){
    return this.wih;
  }

  public double[][] getwho(){
    return this.who;
  }

  public void setwho(double[][] who){
    this.who = who;
  }

  public void setwih(double[][] wih){
    this.wih = wih;
  }
  
}
