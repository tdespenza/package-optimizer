package integration.com.mobiquityinc.packer

import com.mobiquityinc.exception.APIException
import com.mobiquityinc.packer.Packer
import spock.lang.Specification
import spock.lang.Unroll

class PackerIntegrationSpec extends Specification {

    @Unroll
    def "program should run successfully"() {
        setup:
        final ClassLoader classLoader = getClass().getClassLoader()
        final File file = new File(classLoader.getResource("packages.txt").getFile())

        when:
        Packer.pack(file.getAbsolutePath())

        then:
        notThrown(APIException)
    }

    @Unroll
    def "program should throw an APIException"() {
        when:
        Packer.pack("not a file path")

        then:
        thrown(APIException)
    }
}

