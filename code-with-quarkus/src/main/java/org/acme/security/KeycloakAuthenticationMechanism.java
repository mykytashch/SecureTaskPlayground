package org.acme.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.OidcSession;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;
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

    // Получение токена доступа (необходимо уточнить правильный метод)
    public String getAccessToken() {
        // Например, использование oidcSession.getIdToken().getTokenString()
        // Ваша логика получения токена здесь
        return null;
    }

    // Получение информации о пользователе из ID токена
    public AccessToken getUserInfo() {
        // Ваша логика извлечения данных из токена здесь
        return null;
    }

    // Проверка роли пользователя
    public boolean hasRole(String role) {
        return securityIdentity.hasRole(role);
    }

    // Дополнительные методы...
}
