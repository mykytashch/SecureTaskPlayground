package org.acme;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.SecurityIdentity;
import org.acme.security.KeycloakAuthenticationMechanism;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class SecurityConfig {

    private static final Logger LOGGER = Logger.getLogger(SecurityConfig.class);

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    String authServerUrl;

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String clientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String clientSecret;

    public void authenticateUser(@Observes SecurityIdentity identity, UriInfo uriInfo) {
        LOGGER.info("Authentication Mechanism: Keycloak");

        OidcJwtCallerPrincipal principal = identity.getPrincipal().asOidcJwtCallerPrincipal();

        if (principal == null) {
            LOGGER.error("Authentication failed. No principal found.");
            throw new AuthenticationFailedException("Authentication failed");
        }

        String username = principal.getClaim("preferred_username");
        LOGGER.info("Authenticated user: " + username);
    }

    public KeycloakAuthenticationMechanism keycloakAuthenticationMechanism() {
        // Убедитесь, что конструктор соответствует определению класса KeycloakAuthenticationMechanism
        return new KeycloakAuthenticationMechanism();
    }
}
