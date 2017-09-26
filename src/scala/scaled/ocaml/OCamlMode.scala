//
// Scaled OCaml Mode - a Scaled major mode for editing OCaml code
// http://github.com/scaled/ocaml-mode/blob/master/LICENSE

package scaled.ocaml

import scaled._
import scaled.code.Indenter
import scaled.grammar._
import scaled.code.{CodeConfig, Commenter}

object OCamlConfig extends Config.Defs {
  import EditorConfig._
  import CodeConfig._
  import GrammarConfig._

  // maps TextMate grammar scopes to Scaled style definitions
  val effacers = List(
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

  val grammars = resource("ocaml.ndf")(Grammar.parseNDFs)
}

@Major(name="ml",
       tags=Array("code", "project", "ml"),
       pats=Array(".*\\.ml"),
       desc="A major mode for editing OCaml code.")
class OCamlMode (env :Env) extends GrammarCodeMode(env) {

  override def dispose () {} // nada for now

  override def configDefs = OCamlConfig :: super.configDefs
  override def grammars = OCamlConfig.grammars.get
  override def effacers = OCamlConfig.effacers

  override def keymap = super.keymap.
    bind("self-insert-command", "'"); // don't auto-pair single quote

  // override def createIndenter() = new XmlIndenter(buffer, config)
  override val commenter = new OCamlCommenter()
}
