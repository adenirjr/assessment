package com.ea.assessment.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Name: Adenir Silva
 * Date: 2021-01-13
 */
public class FileParserUtils {

    /**
     * Parse row to get the first element to the
     * left of the pipe token.
     *
     * @param row file row
     * @return host
     */
    public static String parseRowIntoHost(final String row) {
        final String[] pipeSplit = row.split("\\|");

        if (pipeSplit != null && pipeSplit.length > 0) {
            final String[] commaSplit = pipeSplit[0].split(",");

            if (commaSplit != null && commaSplit.length > 0) {
                return commaSplit[0];
            }
        }

        return "";
    }

    /**
     * Return the list of existing values to the
     * right side of the pipe token.
     * <p>
     * In case of not numeric, NaN is added to the list.
     *
     * @param row file row
     * @return a sorted list of float values
     */
    public static List<Float> parseRowIntoValues(final String row) {
        final String[] pipeSplit = row.split("\\|");

        if (pipeSplit != null && pipeSplit.length > 0) {
            final String[] commaSplit = pipeSplit[1].split(",");

            if (commaSplit != null) {
                final List<Float> floatList = Arrays.stream(commaSplit)
                        .filter(s -> NumberUtils.isCreatable(s))
                        .map(s -> {
                            return Float.valueOf(s);
                        }).collect(Collectors.toList());

                Collections.sort(floatList);
                return Collections.unmodifiableList(floatList);
            }
        }

        return List.of();
    }
}
