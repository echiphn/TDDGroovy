package org.groovy.database

import groovy.sql.Sql
import org.junit.Test

/**
 * Created by cle on 05/07/15.
 */
class DBSimpleTest {
    @Test
    public void testAccessDatabase() {
        def sql = Sql.newInstance("jdbc:h2:file:./test/resources/db/hello", "sa", "sa", "org.h2.Driver")
        sql.execute("drop table if exists test")
        sql.execute("create table test (id int, value varchar)")
        sql.execute("insert into test values(:id, :value)", [id: 1, value: 'hello'])
        def rows= sql.rows("select * from test")
        assert rows.size() == 1
        assert rows.get(0).id==1
        assert rows.get(0).value=='hello'
        sql.close()
    }
}
