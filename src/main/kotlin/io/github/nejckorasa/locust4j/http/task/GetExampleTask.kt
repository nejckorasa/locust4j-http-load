package io.github.nejckorasa.locust4j.http.task

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.myzhan.locust4j.AbstractTask
import io.github.nejckorasa.locust4j.http.HttpRequests
import io.github.nejckorasa.locust4j.http.config.ConfigurationContext
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder


open class GetExampleTask(private val weight: Int, private val userId: String) : AbstractTask() {

    override fun getWeight(): Int = weight

    override fun getName(): String = "Get example task (user id: $userId)"

    override fun execute() {

        // read base url from configuration
        val httpGet = HttpGet(URIBuilder("${ConfigurationContext.getConfiguration().baseUrl}some/rest/path")
                .setParameter("userId", userId)
                .setParameter("jsonParam1", """ {"a":1546988400000,"b":1548284400000} """)
                .setParameter("jsonParam2", """ {"c":7,"d":0,"e":17,"f":0} """)
                .build())

        // make request and record to Locust
        val (stringResponse, statusCode) = HttpRequests.executeAndRecord(httpGet, name, 200)

        if (statusCode == 200) {
            // do stuff
        }

        // parse json
        val objectMapper = ObjectMapper()
        HttpRequests.objectMapper.readTree(stringResponse)
    }
}
