package io.github.nejckorasa.locust4j.http.task

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.myzhan.locust4j.AbstractTask
import io.github.nejckorasa.locust4j.http.HttpRequests
import io.github.nejckorasa.locust4j.http.config.ConfigurationContext
import org.apache.http.NameValuePair
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.message.BasicNameValuePair
import java.util.*

open class PostExampleTask(private val weight: Int, private val userId: String) : AbstractTask() {

    override fun getWeight(): Int = weight

    override fun getName(): String = "Post Example Task (user $userId)"

    override fun execute() {

        // read base url from configuration
        val httpPost = HttpPost(URIBuilder("${ConfigurationContext.getConfiguration().baseUrl}some/path").build())

        val form = ArrayList<NameValuePair>()

        form.add(BasicNameValuePair("userId", userId))
        form.add(BasicNameValuePair("jsonParam", """ {"id":"id","name":"test"} """))
        form.add(BasicNameValuePair("largeJsonParam", getLargeJsonParam()))

        // make request and record to Locust
        val (stringResponse, statusCode) = HttpRequests.executeAndRecord(httpPost, name)

        if (statusCode == 200) {
            // do stuff
        }

        // parse json
        val objectMapper = ObjectMapper()
        HttpRequests.objectMapper.readTree(stringResponse)
    }

    private fun getLargeJsonParam(): String = """
    {
      "g": 1,
      "h": 3
    }
     """.trimIndent()
}

