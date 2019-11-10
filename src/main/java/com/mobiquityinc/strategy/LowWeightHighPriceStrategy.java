package com.mobiquityinc.strategy;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Item;
import com.mobiquityinc.packer.Package;

import java.util.Comparator;

public final class LowWeightHighPriceStrategy extends PackingStrategy {
    public LowWeightHighPriceStrategy(final Package box) {
        this.box = box;
    }

    @Override
    public final Package packageItems() throws APIException {
        if (box == null || box.getItems() == null || box.getItems().isEmpty()) {
            throw new APIException("ERROR: Invalid Package! Package [ " + box + " ]");
        }

        final Package optimized = new Package(box.getWeight());

        box.getItems()
                .stream()
                .filter(item -> item.getWeight() < box.getWeight())
                .sorted(
                        Comparator.comparingInt(Item::getPrice)
                                .reversed()
                                .thenComparing(Item::getWeight)
                )
                .forEach(item -> {
                    if (optimized.getWeight() > (optimized.getTotalItemWeight() + item.getWeight())) {
                        optimized.addItem(item);
                    }
                });

        return optimized;
    }
}
