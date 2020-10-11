package dule12

import org.tmatesoft.svn.core._
import org.tmatesoft.svn.core.io._
import org.tmatesoft.svn.core.wc._
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory
import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    println("SVNTrunks starting!")
    if (args.length == 4) {
      val url = args(0)
      val username = args(1)
      val password = args(2)
      val checkoutRoot = args(3)
    }
    DAVRepositoryFactory.setup()
  }
}
