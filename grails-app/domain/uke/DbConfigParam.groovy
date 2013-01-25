package uke

class DbConfigParam {
    String name
    String value

    static constraints = {
        value nullable: true
    }

    static mapping = {
    }

}
