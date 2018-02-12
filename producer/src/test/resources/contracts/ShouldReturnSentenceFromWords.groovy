import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {

    description "Should return sentence from words"

    request {
        method POST()
        url("/words")
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body([
                subject : "I",
                verb: "saw",
                article: "an",
                adjective: "incredible",
                noun: "performance"
        ])
        stubMatchers {
            jsonPath('$.subject', byRegex(onlyAlphaUnicode()))
            jsonPath('$.verb', byRegex(onlyAlphaUnicode()))
            jsonPath('$.article', byRegex(onlyAlphaUnicode()))
            jsonPath('$.adjective', byRegex(onlyAlphaUnicode()))
            jsonPath('$.noun', byRegex(onlyAlphaUnicode()))
        }
    }

    response{
        status(200)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body([
                id : "a62fe6d7-f898-44b7-8aba-6eaddece31be",
                sentence : "I saw an incredible performance"
        ])
        testMatchers {
            jsonPath('$.id', byRegex(uuid()))
        }
    }
}