package org.jetbrains.plugins.hocon
package ref

import psi.HString

import com.intellij.patterns.{PlatformPatterns, PsiElementPattern}
import com.intellij.psi.{PsiElement, PsiReferenceContributor, PsiReferenceRegistrar}

import scala.reflect.{ClassTag, classTag}

class HoconJavaReferenceContributor extends PsiReferenceContributor {
  private def pattern[T <: PsiElement : ClassTag]: PsiElementPattern.Capture[T] =
    PlatformPatterns.psiElement(classTag[T].runtimeClass.asInstanceOf[Class[T]])

  override def registerReferenceProviders(registrar: PsiReferenceRegistrar): Unit = {
    registrar.registerReferenceProvider(pattern[HString], new HStringJavaClassReferenceProvider)
    registrar.registerReferenceProvider(pattern[PsiElement], new HoconPropertiesReferenceProvider)
  }
}
