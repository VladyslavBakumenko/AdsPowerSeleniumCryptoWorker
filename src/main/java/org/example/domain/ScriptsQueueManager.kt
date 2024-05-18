package org.example.domain

import org.example.data.utils.dataClasses.AdsPowerProfileScriptData
import org.example.data.utils.enums.AdsPowerProfilesData
import org.example.data.utils.enums.ScriptType
import sun.misc.Queue

class ScriptsQueueManager {

    private val shuffleAdsPowerProfiles = AdsPowerProfilesData.entries.shuffled()

    fun generateScriptsQueue(): MutableList<AdsPowerProfileScriptData> {
        val resultList = mutableListOf<AdsPowerProfileScriptData>()
        shuffleAdsPowerProfiles.forEach {
            resultList.add(AdsPowerProfileScriptData(it, getShuffledScriptQueue()))
        }
        return resultList
    }

    private fun getShuffledScriptQueue(): Queue<ScriptType> {
        val result = Queue<ScriptType>()
        ScriptType.entries.shuffled().forEach {
            result.enqueue(it)
        }
        return result
    }
}