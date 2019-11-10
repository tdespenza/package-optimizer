package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;

/**
 * The base class for implementing packaging expressions.
 *
 * @param <T>
 */
public abstract class InterpreterExpression<T> {
    String sentence;

    public abstract T interpret(InterpreterContext context) throws APIException;

    public void setSentence(final String sentence) {
        this.sentence = sentence;
    }
}
