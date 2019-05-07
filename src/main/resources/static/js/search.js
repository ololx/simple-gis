let MapController = function(_map = null, lat = 0, lon = 0, zoom = 2) {

    let _map = null;
    let _markers = {};

    this.addMarkger(lon, lat, map, zoom, tag = null) {
        let marker = new L.marker([lat, lon]);

        if(tag !== null) {
            marker.bindPopup(tag).openPopup();
        }

        _map.addLayer(marker);

        map.setView([lat, lon], zoom);
    }

    this.getMap = (map) => {
        return _map;
    }

    this.setMap = (map) => {
        _map = L.map(map);

        if(map !== null) {
            setTitleLayer();
        }
    }

    this.isMapExist = () => {
        return this.getMap() !== null ? true : false;
    }

    this.setView = (lat, lon, zoom) => {
        _map.setView([lat, lon], zoom);
    }

    constructor = (map, lat, lon, zoom) => {
        this.setMap(map);

        if(this.isMapExist()) {
            this.setView(lat, lon, zoom);
        }
    }

    setTitleLayer = () => {
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?'
            + 'access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.'
            + 'rJcFIG214AriISLbB6B5aw`,
            {
                maxZoom: 18,
                attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">'
                    + 'OpenStreetMap</a> contributors, <a href="https://creativecommons.org/'
                    + 'licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://'
                    + 'www.mapbox.com/">Mapbox</a>',
                id: 'mapbox.streets'
            }
        ).addTo(_map);
    }
}