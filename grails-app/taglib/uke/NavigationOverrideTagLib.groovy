package uke

import org.codehaus.groovy.grails.plugins.web.taglib.FormTagLib

class NavigationOverrideTagLib {

    static namespace = "uke"
    def navLabelPrefix = 'navigation.'


    def grailsApplication

    def navigationService

    /**
     * Render nav items in a given category
     */
    def navigationRender = { attrs ->
        def org = grailsApplication.mainContext.getBean('NavigationTagLib')

        def css = attrs.'class' ?: ''
        def grp = attrs.group ?: '*'
        def subitems = attrs.subitems ? Boolean.valueOf(attrs.subitems?.toString()) : false
        def id = attrs.id == null ? "navigation_${grp == '*' ? 'all' : grp}" : attrs.id

        def o = out
        o << "<ul class=\"nav ${css}\""
        if (id) {
            o << " id=\"${id.encodeAsHTML()}\""
        }
        o << '>'
        o << org.eachItem(attrs, {
            def title = it.title?.toLowerCase()
            def cls = "${it.active ? 'active ' : ''}${it.first ? 'first ' : ''}${it.last ? 'last' : ''}"
            o << "<li"
            if (cls) o << " class=\"${cls.trim()}\""
            def msgCode = navLabelPrefix + grp + '.' + title
            o << "><a href=\"${it.link.encodeAsHTML()}\">${message(code: msgCode, default: it.title, encodeAs: 'HTML')}</a>"
            if (subitems) {
                o << org.renderSubItems([group: grp, id: '', title: it.title, params: attrs.params])
            }
            o << "</li>"
        })
        o << "</ul>"
    }


    def renderSubItems = { attrs ->
        def org = grailsApplication.mainContext.getBean('NavigationTagLib')

        def grp = attrs.group ?: '*'
        def id = attrs.id == null ? "subnavigation_${grp == '*' ? 'all' : grp}" : attrs.id

        def sectionCode
        if (!attrs.title) {
            // Resolve parent by controller instead
            //attrs.controller = GrailsClassUtils.getLogicalName(controllerName, 'Controller')
            //attrs.remove('title')
            sectionCode = controllerName?.toLowerCase() ?: '_unknown'
        } else {
            sectionCode = attrs.title.toLowerCase()
        }
        def o = out
        o << "<ul class=\"subnavigation\""
        if (id) {
            o << " id=\"${id.encodeAsHTML()}\""
        }
        o << '>'

        o << org.eachSubItem(attrs, { item ->
            def title = item.title?.toLowerCase()
            def cls = "${item.active ? 'subnavigation_active ' : ''}${item.first ? 'subnavigation_first ' : ''}${item.last ? 'subnavigation_last' : ''}"
            o << "<li"
            if (cls) o << " class=\"${cls.trim()}\""
            o << "><a href=\"${item.link.encodeAsHTML()}\">${message(code:'subnavigation.'+grp+'.'+sectionCode+'.'+title, default:item.title, encodeAs:'HTML')}</a></li>"
        })
        o << "</ul>"
    }


}
