package cn.qfys521.simpleTask.utiil

import cn.qfys521.simpleTask.database.DatabaseUtil
import cn.qfys521.simpleTask.database.NPCManager
import java.sql.Connection

class NPCUtil {

    companion object {
        val npcManager = lazy { NPCManager() }
        fun initDatabase(): Connection {
            DatabaseUtil.init()
            return DatabaseUtil.getConnection()
        }
    }

}