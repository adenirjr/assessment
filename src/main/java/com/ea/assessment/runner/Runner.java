package com.ea.assessment.runner;

import com.ea.assessment.utils.FileParserUtils;
import com.ea.assessment.valueobject.SummaryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Name: Adenir Silva
 * Date: 2021-01-13
 */

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    @Value("${file.name}")
    private String fileName;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start processing file");

        final List<String> fileContent = readInputFile();
        final List<SummaryVO> summaryVOList = getSortedByAvgSummaryList(fileContent);

        sortSummaryList(summaryVOList);
        printOutputToConsole(summaryVOList);

        log.info("File Processing Finished");
    }

    /**
     * @param fileContent a list where each item contains
     *                    one input file row
     * @return a list of summary encapsulated in a value object.
     */
    private List<SummaryVO> getSortedByAvgSummaryList(final List<String> fileContent) {
        final Map<String, List<Float>> hostMap =
                fileContent.stream().collect(Collectors.toMap(s -> FileParserUtils.parseRowIntoHost(s),
                        s -> FileParserUtils.parseRowIntoValues(s), (v1, v2) -> v2));

        final List<SummaryVO> summaryVOList =
                hostMap.entrySet().stream()
                        .map(entry -> SummaryVO.builder()
                                .host(entry.getKey())
                                .sortedFloatList(entry.getValue())
                                .build())
                        .collect(Collectors.toList());

        return summaryVOList;
    }

    /**
     * Sorts the list according to the average of each host
     *
     * @param summaryVOList summaryList
     */
    private void sortSummaryList(final List<SummaryVO> summaryVOList) {
        Collections.sort(summaryVOList, new Comparator<SummaryVO>() {
            @Override
            public int compare(SummaryVO o1, SummaryVO o2) {
                return o2.getAverage().compareTo(o1.getAverage());
            }
        });
    }

    /**
     * Read input from working directory
     *
     * @return list of String where each item is one file row
     * @throws Exception
     */
    private List<String> readInputFile() throws Exception {
        return Files.readAllLines(Paths.get(fileName)
                .toAbsolutePath());
    }


    /**
     * Prints the output to the console
     *
     * @param summaryVOList
     */
    private void printOutputToConsole(final List<SummaryVO> summaryVOList) {
        summaryVOList.stream().forEach(summaryVO -> System.out.println(summaryVO.toString()));
    }

}
