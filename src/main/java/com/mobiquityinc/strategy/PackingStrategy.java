package com.mobiquityinc.strategy;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Package;

public abstract class PackingStrategy {
    Package box;

    public abstract Package packageItems() throws APIException;
}
