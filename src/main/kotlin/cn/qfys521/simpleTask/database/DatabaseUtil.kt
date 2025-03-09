package cn.qfys521.simpleTask.database

import java.sql.Connection
import java.sql.DriverManager

/**
 * 数据库工具类，用于管理数据库连接和初始化。
 */
object DatabaseUtil {
    private const val URL = "jdbc:sqlite:plugins/SimpleTask/npcs.db"
    private var connection: Connection? = null

    /**
     * 获取数据库连接。
     *
     * @return 数据库连接
     */
    fun getConnection(): Connection {
        if (connection == null || connection!!.isClosed) {
            connection = DriverManager.getConnection(URL)
        }
        return connection!!
    }

    /**
     * 初始化数据库，创建必要的表。
     */
    fun init() {
        getConnection().createStatement().executeUpdate(
            """
            CREATE TABLE IF NOT EXISTS npcs (
                id TEXT PRIMARY KEY,
                data TEXT NOT NULL
            )
            """
        )
    }
}