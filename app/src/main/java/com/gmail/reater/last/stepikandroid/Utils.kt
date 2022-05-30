package com.gmail.reater.last.stepikandroid

import io.reactivex.Observable
import java.net.HttpURLConnection
import java.net.URL

fun createRequest(url: String) = Observable.create<String> {
    val urlConnection = URL(url).openConnection() as HttpURLConnection
    try {
        // само обращение в сеть
        urlConnection.connect()
        // проверка результата, что он 200
        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK)
            it.onError(RuntimeException(urlConnection.responseMessage))
        else {
            // читаем urlConnection как текст
            val str = urlConnection
                .inputStream
                .bufferedReader()
                .readText()
            it.onNext(str)
        }
    } finally {
        urlConnection.disconnect()
    }
}