package arrow.fir.prototype

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class Registrar : FirExtensionRegistrar() {
  override fun ExtensionRegistrarContext.configurePlugin() {
    +::BestNamePlugin
  }
}