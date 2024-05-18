package org.example.data.utils.dataClasses

import org.example.data.utils.enums.AdsPowerProfilesData
import org.example.data.utils.enums.ScriptType
import sun.misc.Queue

data class AdsPowerProfileScriptData(val adsPowerProfileData: AdsPowerProfilesData, val scriptsQueueList: Queue<ScriptType>)