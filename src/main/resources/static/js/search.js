let MapController = (_map = null, lat = 0, lon = 0, zoom = 2) => {

    let _map;

    this.getMap = (map) => {
        return _map;
    }

    this.setMap = (map) => {
        _map = L.map();.setView([55.04, 82.90], 2);;
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
}