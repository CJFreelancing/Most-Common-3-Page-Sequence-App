package com.chrisjmobileapps.mostcommon3pagesequenceapp


import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.Page
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageListParser
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency
import org.junit.Assert
import org.junit.Test

class PageFrequencyTest {

    @Test
    fun getPageFrequency_testCase1() {

        /*** Given  ***/

        //count
        val mockCount = 2

        //page list
        val mockPageNameList = listOf("Page 1", "Page 2", "Page 3")

        //Page sequence Page1, Page2, Page 3, count 2
        val mockPageSequenceFrequency = PageSequenceFrequency(mockPageNameList, mockCount)

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>(
                mockPageSequenceFrequency)

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase1PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList[0], pageSequenceFrequencyList[0])
    }

    @Test
    fun getPageFrequency_testCase2() {

        /*** Given  ***/

        //count
        val mockCount = 3

        //page list
        val mockPageNameList = listOf("Page 1", "Page 2", "Page 3")

        //Page sequence Page1, Page2, Page 3, count 3
        val mockPageSequenceFrequency = PageSequenceFrequency(mockPageNameList, mockCount)

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>(
                mockPageSequenceFrequency)

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase2PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList[0], pageSequenceFrequencyList[0])
    }

    @Test
    fun getPageFrequency_testCase3() {

        /*** Given  ***/

        //count
        val mockCount1 = 4
        val mockCount2 = 3

        //page list
        val mockPageNameList1 = listOf("Page 10", "Page 11", "Page 12")
        val mockPageNameList2 = listOf("Page 1", "Page 2", "Page 3")

        //Page sequence Page 10, Page 11, Page 12 count 4
        //Page sequence Page 1, Page 2, Page 3, count 3
        val mockPageSequenceFrequency1 = PageSequenceFrequency(mockPageNameList1, mockCount1)
        val mockPageSequenceFrequency2 = PageSequenceFrequency(mockPageNameList2, mockCount2)

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>(
                mockPageSequenceFrequency1,
                mockPageSequenceFrequency2)

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase3PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList[0], pageSequenceFrequencyList[0])
        Assert.assertEquals(mockPageSequenceFrequencyList[1], pageSequenceFrequencyList[1])
    }

    //Size 3 with matching pair
    @Test
    fun getPageFrequency_testCase4() {

        /*** Given  ***/

        //count
        val mockCount1 = 1

        //page list
        val mockPageNameList1 = listOf("Page 1", "Page 2", "Page 3")

        //Page sequence Page 1, Page 2, Page 3, count 3
        val mockPageSequenceFrequency1 = PageSequenceFrequency(mockPageNameList1, mockCount1)

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>(
                mockPageSequenceFrequency1)

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase4PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList[0], pageSequenceFrequencyList[0])
    }

    //Size 3 with no matching pair
    @Test
    fun getPageFrequency_testCase5() {

        /*** Given  ***/

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>()

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase5PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList, pageSequenceFrequencyList)
    }

    //Size/Empty 0 page list
    @Test
    fun getPageFrequency_testCase6() {

        /*** Given  ***/

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>()

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase6PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList, pageSequenceFrequencyList)
    }

    //Size 4 with matching pair
    @Test
    fun getPageFrequency_testCase7() {

        /*** Given  ***/

        //count
        val mockCount1 = 1

        //page list
        val mockPageNameList1 = listOf("Page 1", "Page 2", "Page 3")

        //Page sequence Page 1, Page 2, Page 3, count 3
        val mockPageSequenceFrequency1 = PageSequenceFrequency(mockPageNameList1, mockCount1)

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>(
                mockPageSequenceFrequency1)

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase7PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList[0], pageSequenceFrequencyList[0])
    }

    //Size 4 with matching pair
    @Test
    fun getPageFrequency_testCase8() {

        /*** Given  ***/

        //Create mock page sequence list
        val mockPageSequenceFrequencyList = listOf<PageSequenceFrequency>()

        /*** When ***/

        val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(getTestCase8PageList())

        //Then
        Assert.assertEquals(mockPageSequenceFrequencyList, pageSequenceFrequencyList)
    }

    //Page sequence Page1, Page2, Page 3, count 2
    private fun getTestCase1PageList(): List<Page> {
        return listOf(
                Page("User A", "Page 1"),
                Page("User B", "Page 1"),
                Page("User B", "Page 2"),
                Page("User B", "Page 3"),
                Page("User B", "Page 2"),
                Page("User A", "Page 2"),
                Page("User A", "Page 3"),
                Page("User A", "Page 4"),
                Page("User A", "Page 1"),
                Page("User A", "Page 2"))
    }

    //Page sequence Page1, Page2, Page 3, count 3
    private fun getTestCase2PageList(): List<Page> {
        return listOf(
                Page("User A", "Page 1"),
                Page("User B", "Page 1"),
                Page("User B", "Page 2"),
                Page("User B", "Page 3"),
                Page("User B", "Page 2"),
                Page("User A", "Page 2"),
                Page("User A", "Page 3"),
                Page("User A", "Page 4"),
                Page("User A", "Page 1"),
                Page("User A", "Page 2"),
                Page("User A", "Page 3"))
    }

    //Page sequence Page 10, Page 11, Page 12 count 4
    //Page sequence Page 1, Page 2, Page 3, count 3
    private fun getTestCase3PageList(): List<Page> {
        return listOf(
                Page("User X", "Page 1"),
                Page("User Z", "Page 1"),
                Page("User Z", "Page 2"),
                Page("User Z", "Page 3"),
                Page("User Z", "Page 2"),
                Page("User A", "Page 10"),
                Page("User A", "Page 11"),
                Page("User A", "Page 12"),
                Page("User B", "Page 10"),
                Page("User B", "Page 11"),
                Page("User B", "Page 12"),
                Page("User B", "Page 10"),
                Page("User X", "Page 2"),
                Page("User X", "Page 3"),
                Page("User X", "Page 4"),
                Page("User B", "Page 11"),
                Page("User B", "Page 12"),
                Page("User X", "Page 1"),
                Page("User X", "Page 2"),
                Page("User X", "Page 3"),
                Page("User A", "Page 10"),
                Page("User A", "Page 11"),
                Page("User A", "Page 12"))
    }

    //Page sequence Page1, Page2, Page 3, count 3
    private fun getTestCase4PageList(): List<Page> {
        return listOf(
                Page("User A", "Page 1"),
                Page("User A", "Page 2"),
                Page("User A", "Page 3"))
    }

    //Page sequence Page1, Page2, Page 3, count 3 with different users
    private fun getTestCase5PageList(): List<Page> {
        return listOf(
                Page("User A", "Page 1"),
                Page("User B", "Page 2"),
                Page("User C", "Page 3"))
    }

    //Empty Page sequence
    private fun getTestCase6PageList(): List<Page> {
        return emptyList()
    }

    //Page sequence Page1, Page2, Page 3, count 1 size 4
    private fun getTestCase7PageList(): List<Page> {
        return listOf(
                Page("User A", "Page 1"),
                Page("User B", "Page 2"),
                Page("User A", "Page 2"),
                Page("User A", "Page 3"))
    }

    //Size 4 no match
    private fun getTestCase8PageList(): List<Page> {
        return listOf(
                Page("User B", "Page 1"),
                Page("User B", "Page 2"),
                Page("User A", "Page 2"),
                Page("User A", "Page 3"))
    }
}