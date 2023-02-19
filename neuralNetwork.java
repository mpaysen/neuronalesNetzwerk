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

  //create neuralNetwork with parameters
  public neuralNetwork(int inputnodes, int hiddennodes, int outputnodes, double learningrate) {  
    this.inputnodes = inputnodes;
    this.hiddennodes = hiddennodes;
    this.outputnodes = outputnodes;
    this.learningrate = learningrate;
    this.wih = numja.sub(numja.random( this.inputnodes, this.hiddennodes), 0.5); //create random weight input -> hidden
    this.who = numja.sub(numja.random( this.hiddennodes, this.outputnodes), 0.5); //create random weight hidden -> output
  }

//train neuralNetwork (update weights)
  public void train(List<List<Double>> input_list, List<List<Double>> targets_list) {
    double[][] inputs = input_list.stream().map(l -> l.stream().mapToDouble(Double::doubleValue).toArray()).toArray(double[][]::new); //two dimensional Arraylist to two dimensional array for the inputs
    double[][] targets = targets_list.stream().map(l -> l.stream().mapToDouble(Double::doubleValue).toArray()).toArray(double[][]::new); //two dimensional Arraylist to two dimensional array for the targets

    double[][] hidden_inputs = numja.dot(this.wih, inputs); //matrix multiplikation inputs and "weight input -> hidden" -> hidden_inputs
    double[][] hidden_outputs = numja.sigmoid(hidden_inputs, false); //sigmoid function on hidden_inputs -> hidden_outputs

    double[][] final_inputs = numja.dot(this.who, hidden_outputs); //matrix multiplikation hidden_outputs and "weight hidden -> output" -> final_inputs
    double[][] final_outputs = numja.sigmoid(final_inputs, false); //sigmoid function on final_inputs -> final_outputs

    double[][] output_errors = numja.sub(targets, final_outputs); //sub every double final_outputs from targets to get the error difference
    double[][] hidden_errors = numja.dot(numja.transpose(this.who), output_errors); //matrix multiplikation with transposed "weight hidden -> output" amd output_errors (error difference)

    this.who = numja.add(this.who, numja.dot(numja.dot(numja.dots( numja.dots(output_errors, final_outputs), numja.sub(1.0, final_outputs)), numja.transpose(hidden_outputs)), learningrate)); // update "weight hidden -> output" with leraningrate

    this.wih = numja.add(this.wih, numja.dot(numja.dot(numja.dots( numja.dots(hidden_errors, hidden_outputs), numja.sub(1.0, hidden_outputs)), numja.transpose(inputs)), learningrate)); // update "weight input -> hidden" with leraningrate
  }
//query neuralNetwork
  public double[][] query(List<List<Double>> input_list) {

    double[][] inputs = input_list.stream().map(l -> l.stream().mapToDouble(Double::doubleValue).toArray()).toArray(double[][]::new); //two dimensional Arraylist to two dimensional array for the inputs, no targets

    double[][] hidden_inputs = numja.dot(this.wih, inputs); //matrix multiplikation inputs and "weight input -> hidden" -> hidden_inputs
    
    double[][] hidden_outputs = numja.sigmoid(hidden_inputs, false); //sigmoid function on hidden_inputs -> hidden_outputs

    double[][] final_inputs = numja.dot(this.who, hidden_outputs); //matrix multiplikation hidden_outputs and "weight hidden -> output" -> final_inputs
    double[][] final_outputs = numja.sigmoid(final_inputs, false); //sigmoid function on final_inputs -> final_outputs

    return final_outputs; //final guess
  }

  //get "weight input -> hidden"
  public double[][] getwih(){
    return this.wih;
  }
  //get "weight hidden -> output"
  public double[][] getwho(){
    return this.who;
  }
  //set weight hidden -> output"
  public void setwho(double[][] who){
    this.who = who;
  }
  //set "weight input -> hidden"
  public void setwih(double[][] wih){
    this.wih = wih;
  }
  
}
