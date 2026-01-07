package org.jetbrains.plugins.hocon
package misc

import com.intellij.lang.{BracePair, PairedBraceMatcher}
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class HoconBraceMatcher extends PairedBraceMatcher {

  import org.jetbrains.plugins.hocon.lexer.HoconTokenSets.*
  import org.jetbrains.plugins.hocon.lexer.HoconTokenType.*

  def getPairs: Array[BracePair] = Array(
    new BracePair(LBrace, RBrace, true),
    new BracePair(LBracket, RBracket, false),
    new BracePair(SubLBrace, SubRBrace, false),
  )

  private val AllowsPairedBraceBefore =
    WhitespaceOrComment | Comma | RBrace | RBracket

  def isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType): Boolean =
    AllowsPairedBraceBefore.contains(contextType)

  def getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int =
    openingBraceOffset
}
