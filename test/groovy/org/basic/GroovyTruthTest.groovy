package groovy.org.basic

import static org.junit.Assert.*
import org.junit.Test

/**
 * Created by cle on 04/06/15.
 */
class GroovyTruthTest {
    @Test
    public void testGroovyTruth(){
        if(1){
            assertTrue('any non zero value is true',true)
        }else{
            assertTrue(false)
        }
        if(-1){
            assertTrue('any non zero value is true',true)
        }else{
            assertTrue(false)
        }
        if(!null){
            assertTrue('any non null value is true',true)
        }else{
            assertTrue(false)
        }
        if("john"){
            assertTrue('any non empty string is true',true)
        }else{
            assertTrue(false)
        }
        Map family=[dad:"John",mom:"Jane"]
        if(family){
            assertTrue('any populated map is true',true)
        }else{
            assertTrue(false)
        }
        Map emptyMap=[:]
        if(emptyMap){
            assertFalse(true)
        }else{
            assertFalse('any non populated map is false',false)
        }
    }
}
