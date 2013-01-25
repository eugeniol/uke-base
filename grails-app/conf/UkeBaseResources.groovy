modules = {
    bootstrap {
        dependsOn 'jquery'
        resource url: [plugin: 'uke-base', dir: 'css', file: 'bootstrap.css']
        resource url: [plugin: 'uke-base', dir: 'js/libs', file: "bootstrap.js"]
    }

    "uke-base" {
        resource url: [plugin: 'uke-base', dir: 'js/liebs/jquery', file: "jquery.prettyDate.js"], nominify: true
        resource url: [plugin: 'uke-base', dir: 'js', file: "uke-base.js"]
    }


    modernizr {
        resource url: [plugin: 'uke-base', dir: 'js/libs', file: 'modernizr-2.0.6.js'],
            disposition: 'head'
    }

    jsClass {
        resource url: [plugin: 'uke-base', dir: 'js/libs/src', file: "loader-browser.js"], disposition: 'head'
        resource url: [plugin: 'uke-base', dir: 'js/app', file: "manifest.js"], disposition: 'head'
    }

    mime {
        resource url: 'css/mime/mime-sprite.css'
    }
}