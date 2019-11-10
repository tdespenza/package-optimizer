package com.mobiquityinc.packer;

import java.util.Objects;

public final class Item {
    private final int index;
    private final float weight;
    private final int price;

    public Item(final int index, final float weight, final int price) {
        this.index = index;
        this.weight = weight;
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public float getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return index == item.index &&
                Float.compare(item.weight, weight) == 0 &&
                price == item.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, weight, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "index=" + index +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
