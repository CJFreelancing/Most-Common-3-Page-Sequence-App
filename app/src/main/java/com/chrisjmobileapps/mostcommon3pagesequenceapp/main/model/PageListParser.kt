package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model

import java.util.*

class PageListParser() {

    companion object {
        fun getPageSequenceFrequencyList(pageList: List<Page>): List<PageSequenceFrequency> {
            var pageA: Page?
            var pageB: Page?
            var pageC: Page?
            val pageSequenceFrequencyList = mutableListOf<PageSequenceFrequency>()

            //Key:Page Value:Count
            val threePageSequenceMap = HashMap<List<String>, Int>()

            //Loop through entire Page Sequence Frequency List
            for ((index, page) in pageList.withIndex()) {

                //if not enough room for a 3 page sequence break loop
                if (index > pageList.size - 2) {
                    break
                }

                pageA = pageList[index]
                pageB = null
                pageC = null

                //Get the next 3 page sequences
                for (j in (index + 1) until pageList.size) {

                    //if user not null and is equal to user
                    if (pageB == null) {
                        if (pageA.user.equals(pageList[j].user)) {
                            pageB = pageList[j]
                            continue
                        }
                    }

                    //if user not null and is equal to user
                    if (pageC == null) {
                        if (pageA.user.equals(pageList[j].user)) {
                            pageC = pageList[j]
                            break
                        }
                    }
                }

                //If pageB or pageC null then continue
                if (pageB == null || pageC == null) {
                    continue
                }

                //page list
                val pageNameList = listOf(pageA.page, pageB.page, pageC.page)

                if (threePageSequenceMap.containsKey(pageNameList)) {
                    var count: Int = threePageSequenceMap.get(pageNameList)!!
                    threePageSequenceMap.put(pageNameList, ++count)
                } else {
                    threePageSequenceMap.put(pageNameList, 1)
                }
            }

            if(threePageSequenceMap.size > 0) {
                //loop through map add to PriorityQueue queue
                val priorityQueue: PriorityQueue<PageSequenceFrequency> = PriorityQueue(
                        threePageSequenceMap.size,
                        PageSequenceFrequencyComparator())

                for ((key, value) in threePageSequenceMap) {
                    priorityQueue.offer(PageSequenceFrequency(key, value))
                }

                //Loop through PriorityQueue to add to list
                while (priorityQueue.size != 0) {
                    pageSequenceFrequencyList.add(priorityQueue.poll())
                }
            }

            return pageSequenceFrequencyList
        }

        internal class PageSequenceFrequencyComparator : Comparator<PageSequenceFrequency> {
            override fun compare(p0: PageSequenceFrequency, p1: PageSequenceFrequency): Int {
                if (p0.count > p1.count) {
                    return -1
                }
                if (p0.count < p1.count) {
                    return 1
                }
                return 0
            }
        }
    }
}