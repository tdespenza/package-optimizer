package com.mobiquityinc.interpreter;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Item;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The interpreter context is for parsing the sentence grammar of a given UTF-8 file to get the package weight and list
 * of items to be shipped.
 */
public final class InterpreterContext {

    public final int getPackageWeight(final String sentence) throws APIException {
        if (sentence == null || sentence.trim().equals("")) {
            throw new APIException("ERROR: Invalid sentence! Sentence [ " + sentence + " ]");
        }

        try {
            final int weight = Integer.parseInt(sentence.split(":")[0].trim());

            if (weight > 100) {
                throw new APIException("ERROR: Max weight of 100 exceeded! Weight [ " + weight + " ]");
            }

            return weight;

        } catch (final NumberFormatException e) {
            throw new APIException("ERROR: Invalid sentence! Sentence [ " + sentence + " ]", e);
        }
    }

    public final List<Item> getItems(final String sentence) throws APIException {
        if (sentence == null || "".equals(sentence.trim())) {
            throw new APIException("ERROR: Invalid sentence! Sentence [ " + sentence + " ]");
        }

        try {
            final List<Item> items = parseItems(parseSentence(sentence));

            if (items.size() > 15) {
                throw new APIException("ERROR: Max items of 15 exceeded! Items [ " + items.size() + " ]");
            }

            return items;
        } catch (final ArrayIndexOutOfBoundsException e) {
            throw new APIException("ERROR: Invalid sentence! Sentence [ " + sentence + " ]", e);
        }
    }

    private String[] parseSentence(final String sentence) {
        return sentence
                .split(":")[1] // get the list of items to parse
                .replaceAll("[()]", "") // remove all opening and closing parens
                .trim() // remove all pre and trailing whitespace
                .split(" "); // convert to comma delimited string of items
    }

    private List<Item> parseItems(final String[] items) {
        return Stream.of(items)
                .map(item -> item.split(","))
                // filter out items that have either exceeded max item weight or price
                .filter(attributes -> Float.parseFloat(attributes[1]) < 100 || parseItemPrice(attributes[2]) < 100)
                .map(attributes -> new Item(
                        Integer.parseInt(attributes[0]), // index
                        Float.parseFloat(attributes[1]), // weight
                        parseItemPrice(attributes[2]) // price
                ))
                .collect(Collectors.toList());
    }

    private int parseItemPrice(final String price) {
        return Integer.parseInt(price.replaceAll("[^\\d.]+", ""));
    }
}
