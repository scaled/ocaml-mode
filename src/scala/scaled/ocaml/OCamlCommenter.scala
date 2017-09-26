//
// Scaled OCaml Mode - a Scaled major mode for editing OCaml code
// http://github.com/scaled/ocaml-mode/blob/master/LICENSE

package scaled.ocaml

import scaled._
import scaled.code.Commenter

/** Extends [[Commenter]] with some OCaml smarts. */
class OCamlCommenter extends Commenter {
  import scaled.code.CodeConfig._

  override def linePrefix  = ""
  override def blockOpen = "(*"
  override def blockClose = "*)"
  override def blockPrefix = ""
  override def docPrefix   = ""

  // look for longer prefix first, then shorter
  override def commentDelimLen (line :LineV, col :Int) :Int = {
    if (line.matches(blockPrefixM, col)) blockPrefixM.matchLength
    else if (line.matches(linePrefixM, col)) linePrefixM.matchLength
    else 0
  }
}
