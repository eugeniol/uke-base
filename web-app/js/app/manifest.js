JS.Packages(function () {
    var self = this,
        url = (function () {
            for (els = document.getElementsByTagName("script"), i = els.length - 1; i >= 0; i--)
                if (els[i].src) return els[i].src;
            return '';
        })(),
        params = (function () {
            var p = (url.split('?')[1] || '').split('&'), l = p.length, i = 0, r = {}, q;
            for (; i < l;) {
                q = p[i++].split('=');

                if (q[0])
                    r[q[0]] = q[1];
            }
            return r;
        })();

    JS_VERSION = params.v || window.JS_VERSION || 0.1;

    function file(path) {
        return self.file(path + '?v=' + JS_VERSION)
    }

    function app(path) {
        return file(JSCLASS_PATH + '/../../app/' + path);
    }

    function lib(path) {
        return file(JSCLASS_PATH + '/' + path);
    }

    lib('jquery-1.7.1.js')
        .provides('jQuery');

    lib('tiny_mce/tiny_mce.js')
        .provides('tinyMCE')
        .requires('jQuery');

    lib('jquery.qtip.js')
        .provides('jQuery.fn.qtip')
        .requires('jQuery')
        .styling(CSS_PATH + '/jquery.qtip.css');

    lib('jquery.uniform.js')
        .provides('jQuery.fn.uniform')
        .requires('jQuery')
        .styling(CSS_PATH + '/uniform/uniform.default.css');

    lib('jquery.tools.min.js')
        .provides('jQuery.fn.validator')
        .requires('jQuery');

    lib('jquery.h5validate.js')
        .provides('jQuery.fn.h5Validate')
        .requires('jQuery');

    lib('jquery.html5form-1.5-min.js')
        .provides('jQuery.fn.html5form')
        .requires('jQuery');

    lib('jquery.prettydate.js')
        .provides('jQuery.fn.prettyDate')
        .requires('jQuery');

    lib('jquery.ui.widget.js')
        .provides('jQuery.widget')
        .requires('jQuery');

    lib('jquery.iframe-transport.js')
        .provides('jQueryIframeTransport')
        .requires('jQuery.widget');

    lib('jquery.fileupload.js')
        .provides('jQuery.fn.fileupload')
        .requires('jQueryIframeTransport')
        .requires('jQuery');

    lib('tmpl.min.js')
        .provides('tmpl');

    lib('jquery.fileupload-ui.js')
        .provides('jQuery.blueimpUI')
        .requires('jQuery.widget')
        .requires('tmpl')
        .requires('jQuery.fn.fileupload')
    //.styling(CSS_PATH + '/jquery.fileupload-ui.css');

    lib('swfobject.js')
        .provides('swfobject');
    /*
     lib('uploadify/jquery.uploadify.v2.1.4.js')
     .provides('jQuery.fn.uploadify')
     .requires('swfobject')
     .requires('jQuery')
     .styling(JSCLASS_PATH + '/uploadify/uploadify.css');
     */
    lib('fileuploader.js')
        .provides('qq', 'qq.FileUploader')
        .styling(CSS_PATH + '/fileuploader/fileuploader.css');

    app('widget.js')
        .provides('App.Widget')
        .requires('jQuery');

    app('htmleditor.js')
        .provides('App.HtmlEditor')
        .requires('jQuery', 'JS.Class');

    app('calificaciones.js')
        .provides(
        'App.Calificaciones')
        .requires(
        'JS.Class',
        'JS.Module',
        'JS.Observable',
        'jQuery');


    window.locale = {
        "fileupload":{
            "errors" :{
                "maxFileSize"     :"File is too big",
                "minFileSize"     :"File is too small",
                "acceptFileTypes" :"Filetype not allowed",
                "maxNumberOfFiles":"Max number of files exceeded",
                "uploadedBytes"   :"Uploaded bytes exceed file size",
                "emptyResult"     :"Empty file upload result"
            },
            "error"  :"Error",
            "start"  :"Subir",
            "cancel" :"Cancelar",
            "destroy":"Eliminar"
        }
    };

    /*
     Cargando el tinyMCE bajo demanda
     */
    window.tinyMCEPreInit = {
        base  :JSCLASS_PATH + '/tiny_mce',
        suffix:'',
        query :''
    };


    /*
     JS.Packages(function() { with(this) {
     file('http://www.google.com/jsapi?key=MY_API_KEY')
     .provides('google.load');

     loader(function(cb) { google.load('maps', '2.x', {callback: cb}) })
     .provides('GMap2', 'GClientGeocoder', 'GEvent', 'GLatLng', 'GMarker')
     .requires('google.load');
     }});
     */

    function round_presision(num, dec) {
        return (Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec));
    }

    function format_bytes(bytes) {
        if (typeof bytes !== 'number') {
            return '';
        }

        var i = 0, sizes = [" Bytes", " KB", " MB", " GB", " TB", " PB", " EB", " ZB", " YB"];
        if (bytes == 0) {
            return ('n/a');
        }


        return round_presision(bytes / Math.pow(1024, i = Math.floor(Math.log(bytes) / Math.log(1024))), 2).toString() + sizes[i];
    }
});
