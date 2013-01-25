package uke.base.services

import java.text.Normalizer

class UrlKeyService {

    static transactional = false
    static scope         = "singleton"

    def generateUrlKey(String name) {
        Normalizer.normalize(name, Normalizer.Form.NFD).trim().replaceAll(
            "\\p{IsM}+", "").replaceAll(/[^A-Za-z0-9_-]+/, '-').toLowerCase()
    }
}


