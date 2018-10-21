package io.token.vault.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.vault.authentication.ClientAuthentication
import org.springframework.vault.authentication.ClientCertificateAuthentication
import org.springframework.vault.client.VaultEndpoint
import org.springframework.vault.config.AbstractVaultConfiguration

@Configuration
class VaultConfig extends AbstractVaultConfiguration {

	@Value('http://localhost:8200')
	URI vaultUri

	@Override
	VaultEndpoint vaultEndpoint() {
		return VaultEndpoint.from(vaultUri)
	}

	@Override
	ClientAuthentication clientAuthentication() {
		return new ClientCertificateAuthentication(restOperations())
	}
}
