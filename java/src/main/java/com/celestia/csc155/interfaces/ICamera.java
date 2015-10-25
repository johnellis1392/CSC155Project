
package com.celestia.csc155.interfaces; 

/**
 * Interface defining a camera object
 * @author ellisj
 *
 */
public interface ICamera {
    void translate(final double x, final double y, final double z);
    void rotate(final double roll, final double pitch, final double yaw);
    void scale(final double x, final double y, final double z);
}
