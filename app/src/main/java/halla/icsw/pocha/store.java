package halla.icsw.pocha;

public class store {
    public String adr;
    public String type;
    public String name;
    public double lat;
    public double lng;


public store(String adr,String type,String name, double lat, double lng){
    this.adr=adr;
    this.type=type;
    this.name=name;
    this.lat=lat;
    this.lng=lng;
}


    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
