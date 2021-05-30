package halla.icsw.pocha;

public class store {
    public String adr;
    public int price;
    public String name;
    public double lat;
    public double lng;




    public store(String adr, int price, String name, double lat, double lng){
    this.adr=adr;
    this.price=price;
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

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

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
