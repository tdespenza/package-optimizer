package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.interpreter.InterpreterContext;
import com.mobiquityinc.interpreter.InterpreterExpression;
import com.mobiquityinc.interpreter.ItemExpression;
import com.mobiquityinc.interpreter.PackageWeightExpression;
import com.mobiquityinc.strategy.LowWeightHighPriceStrategy;
import com.mobiquityinc.strategy.PackingStrategy;
import com.mobiquityinc.strategy.StrategyContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {

    private Packer() {
    }

    /**
     * The glue file for running a packaging algorithm
     *
     * @param filePath - absolute file path
     * @return - output from the packaging algorithm
     * @throws APIException - thrown when the given filePath contains invalid data
     */
    public static String pack(final String filePath) throws APIException {
        if (filePath == null || "".equals(filePath.trim())) {
            throw new APIException("ERROR: Invalid absolute file path type! File Path [ " + filePath + " ]");
        }

        try (final Stream<String> stream = Files.lines(Paths.get(filePath))) {
            final List<Package> packages = new LinkedList<>();
            final InterpreterContext interpreterContext = new InterpreterContext();
            final InterpreterExpression<Integer> packageWeightExpression = new PackageWeightExpression();
            final InterpreterExpression<List<Item>> itemExpression = new ItemExpression();
            final PackingStrategy strategy = new LowWeightHighPriceStrategy();
            final StrategyContext strategyContext = new StrategyContext(strategy);
            final String[] lines = stream.toArray(String[]::new);

            int weight;
            List<Item> items;

            for (String line : lines) {
                // set sentences to be evaluated
                packageWeightExpression.setSentence(line);
                itemExpression.setSentence(line);

                // interpret sentences by parsing the package weight and items
                weight = packageWeightExpression.interpret(interpreterContext);
                items = itemExpression.interpret(interpreterContext);

                // create new package and optimize package using algorithm
                strategy.setPackage(new Package(weight, items));
                packages.add(strategyContext.execute());
            }

            return packages.stream()
                    .map(Package::getItemIndices)
                    .collect(Collectors.joining("\n"));

        } catch (final IOException e) {
            throw new APIException("ERROR: Invalid absolute file path type! File Path [ " + filePath + " ]", e);
        }
    }
}
