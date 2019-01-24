package io.github.nejckorasa.locust4j.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.myzhan.locust4j.Locust
import com.google.common.base.Stopwatch
import io.github.nejckorasa.locust4j.http.client.HttpClientSingleton
import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.util.EntityUtils
import org.apache.logging.log4j.LogManager
import java.util.*
import java.util.concurrent.TimeUnit

class HttpRequests {

    companion object {

        private val logger = LogManager.getLogger(HttpRequests::class.java)

        /**
         * Object mapper to read JSON values
         */
        val objectMapper: ObjectMapper = ObjectMapper()

        /**
         * Execute http call and record it to Locust
         */
        fun executeAndRecord(requestBase: HttpRequestBase, name: String): Pair<String?, Int?> {

            val sw = Stopwatch.createStarted()
            var entity: HttpEntity?
            var statusCode: Int? = null
            var stringResponse: String? = null

            try {
                HttpClientSingleton.getInstance().execute(requestBase).use { response ->

                    statusCode = response.statusLine.statusCode
                    entity = response.entity
                    stringResponse = EntityUtils.toString(entity)

                    if (statusCode == 200) {
                        Locust.getInstance().recordSuccess("http", name, sw.stop().elapsed(TimeUnit.MILLISECONDS), response.entity.contentLength)
                    } else {
                        Locust.getInstance().recordFailure("http", name, sw.stop().elapsed(TimeUnit.MILLISECONDS), stringResponse)
                    }

                }
            } catch (e: RuntimeException) {
                val uuid = UUID.randomUUID()
                logger.error("ERROR ID: " + uuid + " - " + Arrays.toString(e.stackTrace))
                Locust.getInstance().recordFailure("http", name, sw.stop().elapsed(TimeUnit.MILLISECONDS), "ERROR ID: " + " - " + e.localizedMessage)
            }

            return Pair(stringResponse, statusCode)
        }

        /**
         * Execute http call and do NOT record it to Locust
         */
        fun execute(requestBase: HttpRequestBase): Pair<HttpEntity?, Int?> {

            var entity: HttpEntity? = null
            var statusCode: Int? = null

            HttpClientSingleton.getInstance().execute(requestBase).use { response ->

                statusCode = response.statusLine.statusCode
                entity = response.entity
            }

            return Pair(entity, statusCode)
        }
    }
}
