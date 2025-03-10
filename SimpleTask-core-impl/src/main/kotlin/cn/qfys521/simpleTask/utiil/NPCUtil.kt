package cn.qfys521.simpleTask.utiil

import cn.qfys521.simpleTask.configure.database.DatabaseUtil
import cn.qfys521.simpleTask.configure.database.NPCManagerDatabaseImpl
import java.sql.Connection

class NPCUtil {

    companion object {
        val npcManager = lazy { NPCManagerDatabaseImpl() }
        fun initDatabase(): Connection {
            DatabaseUtil.init()
            return DatabaseUtil.getConnection()
        }
    }

}