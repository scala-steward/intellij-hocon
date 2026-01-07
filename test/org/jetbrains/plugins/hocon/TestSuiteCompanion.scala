package org.jetbrains.plugins.hocon

import scala.reflect.{classTag, ClassTag}

abstract class TestSuiteCompanion[T: ClassTag] {
  def suite: T =
    classTag[T].runtimeClass.asInstanceOf[Class[T]].getDeclaredConstructor().newInstance()
}
