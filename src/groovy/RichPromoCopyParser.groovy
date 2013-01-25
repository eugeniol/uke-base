/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 05/07/12
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
class RichPromoCopyParser {

    def item
    def pattern = /<!-- ?\[(\w+)\]\[pos:(left|right|center)\] ?-->/

    String promoCopy
    String richPromoCopy

    String getRichPromoCopy() {

        def matcher = promoCopy =~ pattern

        def typeCount = [:],
            globalCount = 0,
            sb = new StringBuffer()

        while (matcher.find()) {
            def type = matcher.group(1)
            def pos = matcher.group(2)
            if (!typeCount.containsKey(type))
                typeCount[type] = 0;

            matcher.appendReplacement(sb, getReplacement(type, pos, typeCount[type], globalCount))

            typeCount[type]++
            globalCount++

        }
        matcher.appendTail(sb)

        sb.toString()
    }

    def getReplacement(type, pos, typeCount, globalCount){

    }

}
