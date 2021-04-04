package model.utilities;

public class Velocity {

    private final double x;
    private final double y;

    public Velocity(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x (pojo)
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y (pojo)
     */
    public double getY() {
        return y;
    }

    /**
     * @param v vector to sum to this
     * @return sum vector
     */
    public Velocity sum(final Velocity v) {
        return new Velocity(x + v.x, y + v.y);
    }

    /**
     * 
     * @return I apply the Pythagorean theorem
     */
    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * 
     * @return normalize the vector
     */
    public Velocity getNormalized() {
        final double module = (double) Math.sqrt(x * x + y * y);
        return new Velocity(x / module, y / module);
    }

    /**
     * 
     * @param fact vector multiplier
     * @return the resulting vector
     */
    public Velocity mul(final double fact) {
        return new Velocity(x * fact, y * fact);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "V2d(" + x + "," + y + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * two V2d are considered equal if their fields are equal
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Velocity other = (Velocity) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
            //return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x); avoid pmd warning
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
            //return Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y); avoid pmd warning
        }
        return true;
    }


}
