/**
  * Created by Denis on 26-Mar-17.
  */
class ChecksumCalculator {
  private var sum = 0

  def add(b: Byte) {
    sum += b
  }

  def checksum: Int = {
    ~(sum & 0xFF) + 1
  }

}
