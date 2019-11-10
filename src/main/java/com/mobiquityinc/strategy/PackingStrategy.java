package com.mobiquityinc.strategy;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Package;

/**
 * The main interface for implementing new packing algorithms.
 */
public abstract class PackingStrategy {
    Package box;

    public abstract Package packageItems() throws APIException;

    public void setPackage(final Package box) {
        this.box = box;
    }
}
