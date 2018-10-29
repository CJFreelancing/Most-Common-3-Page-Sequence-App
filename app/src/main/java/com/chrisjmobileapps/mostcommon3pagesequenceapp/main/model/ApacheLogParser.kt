package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model

import java.util.regex.Pattern


class ApacheLogParser() {

    companion object {
        fun getPageList(apacheLog: String): ArrayList<Page> {

            val userIPAddressList = ArrayList<String>()
            val preUnixPathList = ArrayList<String>()
            val unixPathList = ArrayList<String>()
            val regexPatternList = ArrayList<Pattern>()
            val pageList = ArrayList<Page>()
            val userIPAddressRegex = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])"    // IPv4 IP Address
            val unixRegex1 = "(\")"    // Any Single Character 1
            val unixRegex2 = "(GET)"    // Word 1
            val unixRegex3 = "(\\s+)"    // White Space 1
            val unixRegex4 = "((?:\\/[\\w\\.\\-]+)+)"    // Unix Path 1
            val unixRegex5 = "(.)"    // Any Single Character 2
            val unixRegex6 = "(\\s+)"    // White Space 2
            val unixRegex7 = "(HTTP)"    // Word 2
            val unixRegex8 = "(\\/1\\.1)"    // Unix Path 2
            val unixRegex9 = "(\")"    // Any Single Character 3

            val pattern2 = Pattern.compile(unixRegex1 + unixRegex2 + unixRegex3 + unixRegex4
                    + unixRegex5 + unixRegex6 + unixRegex7 + unixRegex8 + unixRegex9, Pattern.CASE_INSENSITIVE or Pattern.DOTALL)

            regexPatternList.add(Pattern.compile(userIPAddressRegex))

            regexPatternList.add(pattern2)

            for ((index, pattern) in regexPatternList.withIndex()) {

                val match = pattern.matcher(apacheLog)
                while (match.find()) {
                    if (index == 0) {
                        userIPAddressList.add(match.group())
                    } else {
                        preUnixPathList.add(match.group())
                    }
                }
            }

            for ((index, string) in preUnixPathList.withIndex()) {

                val splitPages = string.split(" ")
                unixPathList.add(splitPages[1])
            }

            if (userIPAddressList.size == unixPathList.size) {

                for ((index, unixPath) in unixPathList.withIndex()) {
                    pageList.add(Page(userIPAddressList[index], unixPath))
                }
            }

            return pageList
        }
    }
}