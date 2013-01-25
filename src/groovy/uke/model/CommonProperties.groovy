package uke.model

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 03/07/12
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class CommonProperties implements Serializable, ISearchable {

    String       title
    String       shortDescription
    String       description
    String       urlKey
    List<String> tags = []

    static constraints = {
        urlKey(nullable: true, unique: true, blank: false, size: 1..128, urlKey: true)
        description nullable: true
        shortDescription nullable: true
        title nullable: true, blank: false
        tags nullable: true
    }

    @Override
    Map toSearchableMap() {
        [
            id: this.hasProperty('id') ? this.identity() : null,
            title: title,
            description: description,
            shortDescription: shortDescription,
            urlKey: urlKey,
            tags: tags
        ]
    }
}



