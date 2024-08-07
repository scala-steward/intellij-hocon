package org.jetbrains.plugins.hocon

import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.testFramework.PsiTestUtil.removeContentEntry
import com.intellij.testFramework.{IndexingTestUtil, LightPlatformCodeInsightTestCase}

abstract class HoconSingleModuleTest extends LightPlatformCodeInsightTestCase with HoconTestUtils {
  final def project = getProject
  final def module = getModule
  final def psiManager = getPsiManager

  override def setUp(): Unit = {
    super.setUp()

    val rootModel = ModuleRootManager.getInstance(module).getModifiableModel
    rootModel.addContentEntry(contentRoot).addSourceFolder(contentRoot, false)

    inWriteAction {
      rootModel.commit()
    }

    IndexingTestUtil.waitUntilIndexesAreReady(project)
  }

  override def tearDown(): Unit = {
    removeContentEntry(module, contentRoot)
    super.tearDown()
  }
}
