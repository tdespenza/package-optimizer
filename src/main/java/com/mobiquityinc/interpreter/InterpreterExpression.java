package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;

public abstract class InterpreterExpression<T> {
    String sentence;

    public abstract T interpret(InterpreterContext context) throws APIException;

    public void setSentence(final String sentence) {
        this.sentence = sentence;
    }
}
