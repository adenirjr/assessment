package com.ea.assessment.valueobject;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Name: Adenir Silva
 * Date: 2021-01-13
 */

@Getter
public class SummaryVO {

    private String host;
    private List<Float> sortedFloatList;
    private Float average;

    @Builder
    public SummaryVO(final String host, final List<Float> sortedFloatList) {
        this.host = host;
        this.sortedFloatList = sortedFloatList;
        this.average = calcAverage();
    }

    private Float calcAverage() {
        Float sum = sortedFloatList.stream().reduce(0f, Float::sum);
        return BigDecimal.valueOf(sum).divide(
                BigDecimal.valueOf(sortedFloatList.size()), 1, RoundingMode.HALF_UP)
                .floatValue();
    }

    private Float getMin() {
        return sortedFloatList.get(0);
    }

    private Float getMax() {
        return sortedFloatList.get(sortedFloatList.size() - 1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(host)
                .append(": ")
                .append("Average: ")
                .append(getAverage())
                .append(" Max: ")
                .append(getMax())
                .append(" Min: ")
                .append(getMin());

        return sb.toString();
    }


}