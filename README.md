## Enterprise Alumni Assessment

#### Assumptions
1. The None values are not considered as Zero to not impact the average calculation. They are simply removed from the
 list.

#### Running the application
Instructions for Windows.

* Make sure you have the JAVA_HOME defined as an environment variable.
* Make sure JAVA_HOME is in your path.
* Make sure maven is installed and also in the path.

Before running the application, ensure the input.txt is present in the root directory.

Then run the following command:

```
./mvnw spring-boot:run
```

#### General Comments

1. Value Object and builder pattern were used to emphasize the immutability of the information being processed.
2. The output could have been done through the log.info() method, but I would rather not to mix application output
 with execution logs. In case the logs are suppressed, the output will still the shown.
3. For real world application it would be better to have the file path via command line arguments.
4. Also, to be production ready, there has to be more validation over the input provided. 
5. I have used Float instead of BigDecimal for low memory footprint.
6. For production ready, the input file has to be read as streaming to no cause out of memory in the heap.