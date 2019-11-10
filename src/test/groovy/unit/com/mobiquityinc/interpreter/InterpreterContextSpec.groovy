package unit.com.mobiquityinc.interpreter

import com.mobiquityinc.exception.APIException
import com.mobiquityinc.interpreter.InterpreterContext
import com.mobiquityinc.packer.Item
import spock.lang.Specification
import spock.lang.Unroll

class InterpreterContextSpec extends Specification {
    @Unroll
    def "parse correct max weight of #weight when given '#sentence'"() {
        setup:
        final InterpreterContext context = new InterpreterContext()

        expect:
        context.getPackageWeight(sentence) == weight

        where:
        sentence << [
                "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
                "8 : (1,15.3,€34)",
                "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
                "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)"
        ]
        weight << [81, 8, 75, 56]
    }

    @Unroll
    def "parse correct list of #items when given '#sentence'"() {
        setup:
        final InterpreterContext context = new InterpreterContext()

        expect:
        context.getItems(sentence) == items

        where:
        sentence << [
                "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48),(1,200,€34),(1,200,€200),(1,80,€200)",
                "8 : (1,15.3,€34)",
                "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
                "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)",
        ]
        items << [
                [
                        new Item(1, 53.38, 45),
                        new Item(2, 88.62, 98),
                        new Item(3, 78.48, 3),
                        new Item(4, 72.30, 76),
                        new Item(5, 30.18, 9),
                        new Item(6, 46.34, 48)
                ],
                [
                        new Item(1, 15.30, 34)
                ],
                [
                        new Item(1, 85.31, 29),
                        new Item(2, 14.55, 74),
                        new Item(3, 3.98, 16),
                        new Item(4, 26.24, 55),
                        new Item(5, 63.69, 52),
                        new Item(6, 76.25, 75),
                        new Item(7, 60.02, 74),
                        new Item(8, 93.18, 35),
                        new Item(9, 89.95, 78)
                ],
                [
                        new Item(1, 90.72, 13),
                        new Item(2, 33.80, 40),
                        new Item(3, 43.15, 10),
                        new Item(4, 37.97, 16),
                        new Item(5, 46.81, 36),
                        new Item(6, 48.77, 79),
                        new Item(7, 81.80, 45),
                        new Item(8, 19.36, 79),
                        new Item(9, 6.76, 64)
                ]
        ]
    }

    @Unroll
    def "expect exception when sentence is '#sentence' for package weight"() {
        setup:
        final InterpreterContext context = new InterpreterContext()

        when:
        context.getPackageWeight(sentence)

        then:
        thrown(APIException)

        where:
        sentence << [
                "",
                null,
                "                 ",
                "invalid sentence structure",
                "blue : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
                "200 : (1,15.3,€34)"
        ]
    }

    @Unroll
    def "expect exception when sentence is '#sentence' for list of items"() {
        setup:
        final InterpreterContext context = new InterpreterContext()

        when:
        context.getItems(sentence)

        then:
        thrown(APIException)

        where:
        sentence << [
                "",
                null,
                "                 ",
                "invalid sentence structure",
                "81 : blue",
                "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64), (10,6.76,€64), (11,6.76,€64), (12,6.76,€64), (13,6.76,€64), (14,6.76,€64), (15,6.76,€64), (16,6.76,€64)",
        ]
    }
}
