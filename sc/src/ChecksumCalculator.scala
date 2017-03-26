/** A class that calculates the checksum of bytes. This class
  * is not thread-safe.
  */
class ChecksumCalculator {
  private var sum = 0

  /** Adds the passed <code>Byte</code> to the checksum calculation.
    *
    * @param b the <code>Byte</code> to add
    */
  def add(b: Byte) {
    sum += b
  }

  /** Gets a checksum, for all the <code>Byte</code>s passed
    * to <code>add</code>. The sum of the integer
    * returned by this method, when added to the
    * sum of all the passed bytes will yield zero.
    */
  def checksum: Int = {
    ~(sum & 0xFF) + 1
  }

}
