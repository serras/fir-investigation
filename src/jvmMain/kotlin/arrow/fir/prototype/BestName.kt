package arrow.fir.prototype

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.PsiSourceNavigator.getRawName
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirBasicDeclarationChecker
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.analysis.diagnostics.reportOn
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirPluginKey

class BestNamePlugin(session: FirSession) : FirAdditionalCheckersExtension(session) {
  override val key: FirPluginKey
    get() = Key

  object Key : FirPluginKey()

  override val declarationCheckers: DeclarationCheckers
    get() = object : DeclarationCheckers() {
      override val basicDeclarationCheckers: Set<FirBasicDeclarationChecker>
        get() = setOf(BestName)
    }
}

object BestName : FirBasicDeclarationChecker() {
  override fun check(declaration: FirDeclaration, context: CheckerContext, reporter: DiagnosticReporter) {
    if (declaration.getRawName()?.contains("arrow") == true) {
      reporter.reportOn(declaration.source, FirErrors.WRONG_ANNOTATION_TARGET, "That name is TOO COOL!!", context)
    }
  }
}