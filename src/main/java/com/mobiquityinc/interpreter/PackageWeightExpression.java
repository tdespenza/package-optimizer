package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;

public class PackageWeightExpression extends InterpreterExpression<Integer> {
    public PackageWeightExpression() {
    }

    @Override
    public final Integer interpret(final InterpreterContext context) throws APIException {
        return context.getPackageWeight(sentence);
    }
}
