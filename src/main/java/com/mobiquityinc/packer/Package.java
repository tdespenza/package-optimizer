package com.mobiquityinc.packer;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Package {
    private final int weight;
    private final List<Item> items;

    public Package(final int weight) {
        this.weight = weight;
        this.items = new LinkedList<>();
    }

    public Package(final int weight, final List<Item> items) {
        this.weight = weight;
        this.items = items;
    }

    public int getWeight() {
        return weight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(final Item item) {
        this.items.add(item);
    }

    public float getTotalItemWeight() {
        return this.items.stream()
                .map(Item::getWeight)
                .reduce(0.0f, Float::sum);
    }

    public String getItemIndices() {
        final List<String> indices = this.items
                .stream()
                .map(item -> "" + item.getIndex())
                .collect(Collectors.toCollection(LinkedList::new));

        return indices.isEmpty() ? "-" : String.join(",", indices);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return weight == aPackage.weight &&
                items.equals(aPackage.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, items);
    }

    @Override
    public String toString() {
        return "Package{" +
                "weight=" + weight +
                ", items=" + items +
                '}';
    }
}
