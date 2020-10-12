package dule12

import org.tmatesoft.svn.core._
import org.tmatesoft.svn.core.io._
import org.tmatesoft.svn.core.wc._
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory
import java.io.File
import scala.collection.JavaConversions._

/**
  * Run by passing checkout url, local destination, username.
  * eg. ./svntrunks-native-bin http://svn.example.net/dir1/dir2/ ./ username
  * or just ./svntrunks-native-bin and follow instructions.\
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("SVNTrunks starting!")
    val agg = args.length == 3
    val url = if (agg) args(0) else readLine("SVN url for checkout:")
    val checkoutRoot =
      if (agg) args(1)
      else {
        Some(readLine("Checkout path(default ./):"))
          .filter(_ != "")
          .getOrElse("./")
      }
    println("Username:")
    val username = if (agg) args(2) else readLine()
    println("Password:")
    val standardIn = System.console()
    val password   = new String(standardIn.readPassword())

    println("Checkout url:" + url)
    println("Checkout destination:" + checkoutRoot)

    DAVRepositoryFactory.setup()

    val repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url))
    repository.setAuthenticationManager(
      SVNWCUtil.createDefaultAuthenticationManager(username, password)
    )
    val clientManager = SVNClientManager.newInstance(null, repository.getAuthenticationManager())
    val updateClient  = clientManager.getUpdateClient()

    updateClient.setIgnoreExternals(false)

    val nodeKind = repository.checkPath("", -1)

    if (nodeKind == SVNNodeKind.NONE) {
      println("No entry at requested url:'" + url + "'.")
      System.exit(1)
    } else if (nodeKind == SVNNodeKind.FILE) {
      println(
        "Requested url:'" + url + "' is a file while a directory was expected."
      )
      System.exit(1)
    }

    traverse(updateClient, repository, url, checkoutRoot, "")
    println("Repository latest revision:" + repository.getLatestRevision())

  }

  def traverse(
      updateClient: SVNUpdateClient,
      repository: SVNRepository,
      url: String,
      destRootPath: String,
      repoPath: String
  ): Unit = {

    println(repoPath);

    updateClient.doCheckout(
      SVNURL.parseURIDecoded(url + "/" + repoPath),
      new File(destRootPath + (if (repoPath != "") "/" else "") + repoPath),
      SVNRevision.UNDEFINED,
      SVNRevision.HEAD,
      SVNDepth.FILES,
      false
    )

    val entries = repository.getDir(repoPath, -1, null, null.asInstanceOf[java.util.Collection[_]])
    for (entry <- entries.toIterable.asInstanceOf[Iterable[SVNDirEntry]]) {
      if (
        !entry.getName().equalsIgnoreCase("branches") && !entry
          .getName()
          .equalsIgnoreCase("tags")
      ) {
        if (entry.getKind() == SVNNodeKind.DIR) {
          traverse(
            updateClient,
            repository,
            url,
            destRootPath,
            if (repoPath == "") entry.getName()
            else repoPath + "/" + entry.getName()
          )
        }
      }
    }
  }
}
