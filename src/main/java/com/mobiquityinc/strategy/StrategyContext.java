package com.mobiquityinc.strategy;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Package;

/**
 * The package strategy context handles the execution of the different package strategies.
 */
public final class StrategyContext {
    private PackingStrategy strategy;

    public StrategyContext() {
    }

    public void setStrategy(final PackingStrategy strategy) {
        this.strategy = strategy;
    }

    public final Package execute() throws APIException {
        return strategy.packageItems();
    }
}
