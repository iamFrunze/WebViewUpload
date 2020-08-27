package testchaf.apptestest.utils

import org.jsoup.Jsoup

suspend fun pars(url: String): String {
    val doc = Jsoup.connect(url)
        .userAgent("Chrome/4.0.249.0 Safari/532.5")
        .get()
    return doc.text()
}