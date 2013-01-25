package uke.model

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 19/06/12
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class LatLng implements ISearchable {
    double lat
    double lng

    static constraints = {
        lat nullable: true
        lng nullable: true
    }

    public static LatLng parse(String s) {
        def cords = s.toString().split(',')
        if (cords.size() == 2) {
            try {
                cords = cords.collect { Double.parseDouble(it) }
                return new LatLng(lat: cords[0], lng: cords[1])
            }
            catch (NumberFormatException nfe) {
            }
        }
    }

    LatLng() {

    }

    LatLng(double latitude, double longitude) {
        setLat(latitude)
        setLng(longitude)
    }


    String toUrlValue() {
        lat.toString() + ',' + lng.toString()
    }

    @Override
    Map toSearchableMap() {
        [lat: lat, lon: lng]
    }


}




