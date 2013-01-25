(function ($) {
    // pretty date configuration
    $.prettyDate.messages = {
        now:"Menos de un minuto",
        minute:"Hace un minuto",
        minutes:$.prettyDate.template("Hace {0} minutos"),
        hour:"Aproximadamente una hora",
        hours:$.prettyDate.template("Hace {0} horas"),
        yesterday:"Ayer",
        days:$.prettyDate.template("Hace {0} días"),
        weeks:$.prettyDate.template("Hace {0} semanas")
    };

/*    $.ajax({
        complete:function () {
            $(".prettyDate").prettyDate();
        }
    });
  */
    $(function () {
        $(".prettyDate").prettyDate();
    });

    /**
     * no es necesario porque grails.mime.use.accept.header = true
     */
    /*
    $.ajaxSetup({
        beforeSend:function (jqXHR, options) {
            if (options.dataType == 'json') {
                jqXHR.setRequestHeader("Content-Type", "application/json; charset=utf-8");
            }
        }
    });
    */
})(jQuery);
