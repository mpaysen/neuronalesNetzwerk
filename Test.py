import numpy

def query():
    inputs_list = [1.0, 1.0, 2.0]
    target_list = [1.0, 2.0, 4.0]
    inputs = numpy.array(inputs_list, ndmin=2).T
    target = numpy.array(target_list, ndmin=2).T
    print(target * inputs)

    print(numpy.transpose(inputs))

    data_file = open("mnist_train_100.csv")
    training_data_list = data_file.readlines()
    data_file.close()
    for record in training_data_list:
        all_values = record.split(',')

    print(numpy.array(inputs_list, ndmin=2).T)

    onodes = 10
    targets = numpy.zeros(onodes) + 0.01
    targets[int()]

query()
