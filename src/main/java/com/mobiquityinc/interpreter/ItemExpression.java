package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Item;

import java.util.List;

/**
 * This is used for interpreting all the items in a given sentence. If the sentence doesn't meet the specified sentence
 * structure, then an {@code APIException} will be thrown.
 */
public final class ItemExpression extends InterpreterExpression<List<Item>> {
    public ItemExpression() {
    }

    @Override
    public final List<Item> interpret(final InterpreterContext context) throws APIException {
        return context.getItems(sentence);
    }
}
