package com.ea.assessment.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Name: Adenir Silva
 * Date: 2021-01-13
 */
public class SummaryTests {

    @Test
    public void summary_givenDummyValidInput_calculateCorrectMaxMinAvg() {
        final List<Float> floatList = List.of(2f, 4f, 2f, 4f);
        final String host = "n10";

        final SummaryVO summaryVO = SummaryVO.builder().host(host).sortedFloatList(floatList).build();

        Assertions.assertEquals("n10: Average: 3.0 Max: 4.0 Min: 2.0", summaryVO.toString());
    }
}
