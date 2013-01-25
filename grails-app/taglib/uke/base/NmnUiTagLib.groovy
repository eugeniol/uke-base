package uke.base

import uke.ImageAsset


class NmnUiTagLib {
    static namespace = 'ui'

    def bodyClass = {attrs ->
        if (this.request.globalBodyClass == null)
            this.request.globalBodyClass = []

        if (attrs.'class')
            this.request.globalBodyClass << attrs.'class'
        else
            out << this.request.globalBodyClass.join(' ')
    }

    def prettyDate = {attrs ->
        def date = attrs.date

        out << '<span class="prettyDate" title="' <<
            g.formatDate(format: 'yyyy-MM-dd HH:mm:ss', date: date) << '">' <<
            g.formatDate(format: 'yyyy-MM-dd HH:mm:ss', date: date) << '</span>'
    }

    def jsTrigger = {attrs ->
        def event = attrs.remove('event'),
            data = attrs.data,
            str = "console.log('trigger: ', '$event'); jQuery(document).trigger('$event'"
        if (data)
            str += ',' + data.encodeAsJSON()
        str += ");"

        r.script(null, { str })
    }

    def jsParam   = { attr ->
        def var = attr.var,
            value = attr.value,
            str = "${var}=\"${value.encodeAsJavaScript()}\";"
        r.script([:], { str })
    }

    /**
     * @attr item
     * @attr width
     * @attr height
     * @attr title
     * @attr alt
     */
    def img = {attrs, body ->
        def item = attrs.remove('item')
/*        if (item.hasProperty('image')) {
            item = item.image
        }
  */
        if (item instanceof ImageAsset) {
            def size = item.resize(attrs.width?.toInteger() ?: 0, attrs.height?.toInteger() ?: 0)
            attrs.src = createLink(
                controller: 'imageAsset',
                action: 'view',
                id: item.id,
                params: [width: size.width, height: size.height]
            )
            out << '<img '
            outputAttributes(attrs + [width: size.width, height: size.height], out)
            out << '/>'
        }
        else {
            out << '<img class="not-found"/>'
        }
    }

    /**
     * @attr item
     * @attr width
     * @attr height
     * @attr title
     * @attr alt
     */
    void outputAttributes(attrs, writer) {
        attrs.remove('tagName') // Just in case one is left
        attrs.each { k, v ->
            writer << k
            writer << '="'
            writer << v.encodeAsHTML()
            writer << '" '
        }
    }
}
