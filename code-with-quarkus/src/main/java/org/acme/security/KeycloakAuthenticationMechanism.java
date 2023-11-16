package org.acme.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.OidcSession;
import io.quarkus.security.identity.SecurityIdentity;
import org.keycloak.representations.AccessToken;

@ApplicationScoped
public class KeycloakAuthenticationMechanism {

    @Inject
    OidcSession oidcSession;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    @IdToken
    JsonWebToken idToken;

    // Получение имени пользователя
    public String getUserName() {
        return securityIdentity.getPrincipal().getName();
    }

    // Получение токена доступа
    public String getAccessToken() {
        return oidcSession.getTokens().getAccessToken();
    }

    // Получение информации о пользователе из ID токена
    public AccessToken getUserInfo() {
        return idToken.claim("userinfo").as(AccessToken.class);
    }

    // Проверка роли пользователя
    public boolean hasRole(String role) {
        return securityIdentity.hasRole(role);
    }

    // Добавьте здесь другие методы, которые вам нужны для работы с Keycloak
}
