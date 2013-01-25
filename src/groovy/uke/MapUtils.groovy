package uke

import org.springframework.context.ApplicationEvent

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 17/05/12
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
class MapUtils {

        static Object findRecursive(Map map, Closure test) {
        test(map) ? map : map.findResult { k, v ->
            if (v instanceof Map)
                findRecursive v, test
            else if (v instanceof List)
                v.findResult { findRecursive it, test }
        }
    }
}




