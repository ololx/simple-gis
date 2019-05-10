let Map = function(mapContainer = null, lat = 84, lon = 55, zoom = 4) {

    let Coordinates = function(latitude = null, longitude = null) {

        let lat = 0;
        let lon = 0;

        this.getLon = () => {
            return !lon ? null : lon;
        }

        this.setLon = (longitude) => {
            lon = longitude !== null ? longitude : false;
        }

        this.getLat = () => {
            return !lat ? null : lat;
        }

        this.setLat = (latitude) => {
            lat = latitude !== null ? latitude : false;
        }

        this.getCoord = () => {
            return [
                this.getLat(),
                this.getLon()
            ];
        }

        this.isEmpty = () => {
            return !lon || !lat ? true : false;
        }

        init = (latitude, longitude) => {
            this.setLat(latitude);
            this.setLon(longitude);
        }

        init(latitude, longitude);
    }

    let Marker = function(coordinates = new Coordinates()) {

        let coords = {};
        let marker = null;

        this.getCoordinates = () => {
            return coords;
        }

        this.setCoordinates = (coordinates) => {
            coords = coordinates;
        }

        this.getLat = () => {
            return coords.getLat();
        }

        this.setLat = (latitude) => {
            coords.setLat();
        }

        this.getLon = () => {
            return coords.getLon();
        }

        this.setLat = (longitude) => {
            coords.setLon();
        }

        this.getMarker = () => {
            return marker;
        }

        this.setMarker = (markerView = null) => {
            marker = markerView !== null ? markerView : new L.marker(coords.getCoord());
        }

        init = (coordinates) => {
            this.setCoordinates(coordinates);
            this.setMarker();
        }

        init(coordinates);
    }

    let map = null;
    let markers = [];

    this.addMarker = (lon, lat, tag = null, zoom = 10) => {
        markers.push(new Marker(new Coordinates(lat, lon)));

        if(tag !== null) {
            markers[markers.length - 1].getMarker().bindPopup(tag).openPopup();
        }

        map.addLayer(markers[markers.length - 1].getMarker());
        map.setView([lat, lon], zoom);
    }

    this.clear = () => {
        markers.forEach((eachMarker, i) => {
            map.removeLayer(eachMarker.getMarker());
            markers.splice(i, 1);
        });
    }

    this.getMap = () => {
        return map;
    }

    this.setMap = (mapContainer) => {
        map = L.map(mapContainer);

        if(mapContainer !== null) {
            setTitleLayer();
        }
    }

    this.isExist = () => {
        return this.getMap() !== null ? true : false;
    }

    this.setView = (lat, lon, zoom) => {
        map.setView([lat, lon], zoom);
    }

    init = (mapContainer, lat, lon, zoom) => {
        if(mapContainer !== null) {
            this.setMap(mapContainer);
        }

        if(this.isExist()) {
            this.setView(lat, lon, zoom);
        }
    }

    setTitleLayer = () => {
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
            maxZoom: 18,
            attribution: 'mapContainer data &copy; <a href="https://www.openstreetmapContainer.org/">OpenStreetmap</a> contributors, ' +
                '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                'Imagery © <a href="https://www.mapbox.com/">mapbox</a>',
            id: 'mapbox.streets'
        }).addTo(map);
    }

    init(mapContainer, lat, lon, zoom);
}

let map = new Map();

console.log("1: " + map.getMap());
