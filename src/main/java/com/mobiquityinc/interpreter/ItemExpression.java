package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Item;

import java.util.List;

public final class ItemExpression extends InterpreterExpression<List<Item>> {
    public ItemExpression() {
    }

    @Override
    public final List<Item> interpret(final InterpreterContext context) throws APIException {
        return context.getItems(sentence);
    }
}
