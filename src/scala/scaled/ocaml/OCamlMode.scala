//
// Scaled OCaml Mode - a Scaled major mode for editing OCaml code
// http://github.com/scaled/ocaml-mode/blob/master/LICENSE

package scaled.ocaml

import scaled._
import scaled.code.Indenter
import scaled.grammar._
import scaled.code.{CodeConfig, Commenter}

@Plugin(tag="textmate-grammar")
class OCamlGrammarPlugin extends GrammarPlugin {
  import EditorConfig._
  import CodeConfig._

  override def grammars = Map("source.ocaml" -> "ocaml.ndf")

  override def effacers = List(
    effacer("comment.line", commentStyle),
    effacer("comment.block.string", stringStyle),
    effacer("comment.block", docStyle),
    effacer("constant", constantStyle),
    effacer("invalid", warnStyle),
    effacer("keyword", keywordStyle),
    effacer("string", stringStyle),
    effacer("variable", variableStyle),
    effacer("entity.name.function", functionStyle),
    effacer("entity.name.type.module", moduleStyle),
    effacer("entity.name.type.variant", typeStyle),
    effacer("support.other.module", moduleStyle),
    effacer("storage", variableStyle)
  )
}

@Major(name="ml",
       tags=Array("code", "project", "ml"),
       pats=Array(".*\\.ml", ".*\\.mli"),
       desc="A major mode for editing OCaml code.")
class OCamlMode (env :Env) extends GrammarCodeMode(env) {

  override def dispose () :Unit = {} // nada for now

  override def langScope = "source.ocaml"

  override def keymap = super.keymap.
    bind("self-insert-command", "'"); // don't auto-pair single quote

  // override def createIndenter() = new XmlIndenter(buffer, config)
  override val commenter = new OCamlCommenter()
}
