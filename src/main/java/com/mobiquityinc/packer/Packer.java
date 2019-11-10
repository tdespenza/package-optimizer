package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.interpreter.InterpreterContext;
import com.mobiquityinc.interpreter.InterpreterExpression;
import com.mobiquityinc.interpreter.ItemExpression;
import com.mobiquityinc.interpreter.PackageWeightExpression;
import com.mobiquityinc.strategy.LowWeightHighPriceStrategy;
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

    public static String pack(final String filePath) throws APIException {
        if (filePath == null || "".equals(filePath.trim())) {
            throw new APIException("ERROR: Invalid absolute file path type! File Path [ " + filePath + " ]");
        }

        try (final Stream<String> stream = Files.lines(Paths.get(filePath))) {
            final List<Package> packages = new LinkedList<>();
            final InterpreterContext interpreterContext = new InterpreterContext();
            final InterpreterExpression<Integer> packageWeightExpression = new PackageWeightExpression();
            final InterpreterExpression<List<Item>> itemExpression = new ItemExpression();
            final StrategyContext strategyContext = new StrategyContext();
            final String[] lines = stream.toArray(String[]::new);

            int weight;
            List<Item> items;

            for (String line : lines) {
                packageWeightExpression.setSentence(line);
                itemExpression.setSentence(line);
                weight = packageWeightExpression.interpret(interpreterContext);
                items = itemExpression.interpret(interpreterContext);

                strategyContext.setStrategy(
                        new LowWeightHighPriceStrategy(
                                new Package(weight, items)
                        )
                );

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