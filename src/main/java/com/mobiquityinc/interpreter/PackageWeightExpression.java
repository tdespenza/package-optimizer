package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;

/**
 * This is used for interpreting the package weight in a given sentence. If the sentence doesn't meet the specified sentence
 * structure, then an {@code APIException} will be thrown.
 */
public class PackageWeightExpression extends InterpreterExpression<Integer> {
    public PackageWeightExpression() {
    }

    @Override
    public final Integer interpret(final InterpreterContext context) throws APIException {
        return context.getPackageWeight(sentence);
    }
}
