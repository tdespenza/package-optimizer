package unit.com.mobiquityinc.strategy

import com.mobiquityinc.exception.APIException
import com.mobiquityinc.packer.Item
import com.mobiquityinc.packer.Package
import com.mobiquityinc.strategy.LowWeightHighPriceStrategy
import com.mobiquityinc.strategy.PackingStrategy
import spock.lang.Specification
import spock.lang.Unroll

class LowWeightHighPriceStrategySpec extends Specification {
    @Unroll
    def "determine correct packaging of #packaged when given '#box'"() {
        setup:
        final PackingStrategy strategy = new LowWeightHighPriceStrategy(box)

        expect:
        strategy.packageItems() == packaged

        where:
        box << [
                new Package(81, [
                        new Item(1, 53.38, 45),
                        new Item(2, 88.62, 98),
                        new Item(3, 78.48, 3),
                        new Item(4, 72.30, 76),
                        new Item(5, 30.18, 9),
                        new Item(6, 46.34, 48)
                ]),
                new Package(8, [
                        new Item(1, 15.30, 34)
                ]),
                new Package(75, [
                        new Item(1, 85.31, 29),
                        new Item(2, 14.55, 74),
                        new Item(3, 3.98, 16),
                        new Item(4, 26.24, 55),
                        new Item(5, 63.69, 52),
                        new Item(6, 76.25, 75),
                        new Item(7, 60.02, 74),
                        new Item(8, 93.18, 35),
                        new Item(9, 89.95, 78),
                ]),
                new Package(56, [
                        new Item(1, 90.72, 13),
                        new Item(2, 33.80, 40),
                        new Item(3, 43.15, 10),
                        new Item(4, 37.97, 16),
                        new Item(5, 46.81, 36),
                        new Item(6, 48.77, 79),
                        new Item(7, 81.80, 45),
                        new Item(8, 19.36, 79),
                        new Item(9, 6.76, 64)
                ])
        ]
        packaged << [
                new Package(81, [
                        new Item(4, 72.30, 76)
                ]),
                new Package(8, []),
                new Package(75, [
                        new Item(2, 14.55, 74),
                        new Item(7, 60.02, 74)
                ]),
                new Package(56, [
                        new Item(8, 19.36, 79),
                        new Item(9, 6.76, 64)
                ])
        ]
    }

    @Unroll
    def "expect exception when a package is null"() {
        setup:
        final PackingStrategy strategy = new LowWeightHighPriceStrategy(box)

        when:
        strategy.packageItems()

        then:
        thrown(APIException)

        where:
        box << [
                new Package(81, null),
                null
        ]
    }
}
