package name.mikulskibartosz.bashorgpl

import java.util.concurrent.atomic.AtomicInteger

class CountItems {
  private val countedItems = new AtomicInteger(0)

  def add(): Unit = countedItems.incrementAndGet()

  def numberOfItems: Int = countedItems.intValue()
}
