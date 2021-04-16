package model.utilities;

import java.io.Serializable;

/**
 * represents a vectors to represent a direction in the plane.
 */
public class DirVector implements Serializable {

    private static final long serialVersionUID = -5517208558432027459L;
    private final double x;
    private final double y;

    public DirVector(final double x, final double y) {
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
    public DirVector sum(final DirVector v) {
        return new DirVector(x + v.x, y + v.y);
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
    public DirVector getNormalized() {
        final double module = (double) Math.sqrt(x * x + y * y);
        return new DirVector(x / module, y / module);
    }

    /**
     * 
     * @param fact vector multiplier
     * @return the resulting vector
     */
    public DirVector mul(final double fact) {
        return new DirVector(x * fact, y * fact);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DirVector(" + x + "," + y + ")";
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
        final DirVector other = (DirVector) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        } else if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }


}
