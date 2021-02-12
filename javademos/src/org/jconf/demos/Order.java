package org.jconf.demos;

import java.util.Date;

/**
 *
 * @author serch
 */
public class Order {
    private final char type;
    private final float price;
    private final float amount;
    private final String name;
    private final Date time;
    private final byte txdata[] = new byte[256];

    public Date getTime() {
        return time;
    }

    public byte[] getTxdata() {
        return txdata;
    }
 
    public Order(String name, char type, float price, float amount) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
        time = new Date();
//        Demo01MemoryLeak.rnd.nextBytes(txdata);
    }

    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.type;
        hash = 59 * hash + Float.floatToIntBits(this.price);
        hash = 59 * hash + Float.floatToIntBits(this.amount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.type != other.type) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (Float.floatToIntBits(this.amount) != Float.floatToIntBits(other.amount)) {
            return false;
        }
        return true;
    }
    
    public String getJson(){
        return new String("{name : \"" + name + "\", type : '" + type + 
                "', price: " + price + ", amount : " + amount + ", time : "+time+"}");
    }
    
}
