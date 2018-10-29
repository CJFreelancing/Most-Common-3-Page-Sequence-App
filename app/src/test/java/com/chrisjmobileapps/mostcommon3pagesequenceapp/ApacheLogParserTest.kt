package com.chrisjmobileapps.mostcommon3pagesequenceapp

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.ApacheLogParser
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.Page
import junit.framework.Assert
import org.junit.Test

class ApacheLogParserTest {

    val apacheLog = """
        123.4.5.9 - - [03/Sep/2013:18:34:48 -0600] "GET /team/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.6 - - [03/Sep/2013:18:34:58 -0600] "GET /products/car/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.8 - - [03/Sep/2013:18:35:08 -0600] "GET /products/desk/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.6 - - [03/Sep/2013:18:35:18 -0600] "GET /products/desk/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.9 - - [03/Sep/2013:18:35:28 -0600] "GET /products/phone/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.8 - - [03/Sep/2013:18:35:38 -0600] "GET /products/phone/ HTTP/1.1" 500 821 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.2 - - [03/Sep/2013:18:35:48 -0600] "GET /access/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.2 - - [03/Sep/2013:18:35:58 -0600] "GET /products/desk/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.4 - - [03/Sep/2013:18:36:08 -0600] "GET /products/desk/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.8 - - [03/Sep/2013:18:36:18 -0600] "GET /contact/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.5 - - [03/Sep/2013:18:36:28 -0600] "GET /about/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.7 - - [03/Sep/2013:18:36:38 -0600] "GET /login/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.7 - - [03/Sep/2013:18:36:48 -0600] "GET /products/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.1 - - [03/Sep/2013:18:36:58 -0600] "GET /products/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.1 - - [03/Sep/2013:18:37:08 -0600] "GET /products/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.4 - - [03/Sep/2013:18:37:18 -0600] "GET /team/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.4 - - [03/Sep/2013:18:37:28 -0600] "GET /team/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.9 - - [03/Sep/2013:18:37:38 -0600] "GET /team/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.3 - - [03/Sep/2013:18:37:48 -0600] "GET /about/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.1 - - [03/Sep/2013:18:37:58 -0600] "GET /admin/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.2 - - [03/Sep/2013:18:38:08 -0600] "GET /team/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.1 - - [03/Sep/2013:18:38:18 -0600] "GET /about/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.3 - - [03/Sep/2013:18:38:28 -0600] "GET /products/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.4 - - [03/Sep/2013:18:38:38 -0600] "GET /products/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.1 - - [03/Sep/2013:18:38:48 -0600] "GET /products/car/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.6 - - [03/Sep/2013:18:38:58 -0600] "GET /jobs/ HTTP/1.1" 500 821 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        123.4.5.1 - - [03/Sep/2013:18:39:08 -0600] "GET /jobs/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.4 - - [03/Sep/2013:18:39:18 -0600] "GET /contact/ HTTP/1.1" 500 821 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.6 - - [03/Sep/2013:18:39:28 -0600] "GET /contact/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.7 - - [03/Sep/2013:18:39:38 -0600] "GET /jobs/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36"
        123.4.5.7 - - [03/Sep/2013:18:39:48 -0600] "GET /contact/ HTTP/1.1" 200 3327 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:23.0) Gecko/20100101 Firefox/23.0"
        """

    val emptyLog = ""

    @Test
    fun getPageListTest1() {

        /*** Given ***/

        /*** When ***/
        val pageList = ApacheLogParser.getPageList(apacheLog)

        /*** Then ***/
        Assert.assertEquals(getMockPageList(), pageList)
    }

    @Test
    fun getPageListTest2() {

        /*** Given ***/

        val mockPageList = ArrayList<Page>()


        /*** When ***/
        val pageList = ApacheLogParser.getPageList(emptyLog)

        /*** Then ***/
        Assert.assertEquals(mockPageList, pageList)
    }

    private fun getMockPageList() : List<Page> {
        return listOf(
                Page("123.4.5.9", "/team/"),
                Page("123.4.5.6", "/products/car/"),
                Page("123.4.5.8", "/products/desk/"),
                Page("123.4.5.6", "/products/desk/"),
                Page("123.4.5.9", "/products/phone/"),
                Page("123.4.5.8", "/products/phone/"),
                Page("123.4.5.2", "/access/"),
                Page("123.4.5.2", "/products/desk/"),
                Page("123.4.5.4", "/products/desk/"),
                Page("123.4.5.8", "/contact/"),
                Page("123.4.5.5", "/about/"),
                Page("123.4.5.7", "/login/"),
                Page("123.4.5.7", "/products/"),
                Page("123.4.5.1", "/products/"),
                Page("123.4.5.1", "/products/"),
                Page("123.4.5.4", "/team/"),
                Page("123.4.5.4", "/team/"),
                Page("123.4.5.9", "/team/"),
                Page("123.4.5.3", "/about/"),
                Page("123.4.5.1", "/admin/"),
                Page("123.4.5.2", "/team/"),
                Page("123.4.5.1", "/about/"),
                Page("123.4.5.3", "/products/"),
                Page("123.4.5.4", "/products/"),
                Page("123.4.5.1", "/products/car/"),
                Page("123.4.5.6", "/jobs/"),
                Page("123.4.5.1", "/jobs/"),
                Page("123.4.5.4", "/contact/"),
                Page("123.4.5.6", "/contact/"),
                Page("123.4.5.7", "/jobs/"),
                Page("123.4.5.7", "/contact/"))
    }
}