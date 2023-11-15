package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.AccessTokenResponse;

import java.util.Collections;

@ApplicationScoped
public class KeycloakService {

    private final String serverUrl = "http://localhost:8180/auth";
    private final String realm = "realm_task_project";
    private final String clientId = "mykyta_client";
    private final String clientSecret = "g15j4bVo2HDaM77ZtNJly3VDE8qroHYG";
    private final String adminUsername = "mykyta"; // Замените на имя администратора Keycloak
    private final String adminPassword = "1232"; // Замените на пароль администратора Keycloak

    private Keycloak keycloakAdminClient() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(adminUsername)
                .password(adminPassword)
                .build();
    }

    public void createUser(User user) {
        try (Keycloak keycloak = keycloakAdminClient()) {
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(user.getUsername());
            userRepresentation.setEmail(user.getEmail());
            userRepresentation.setEnabled(true);

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(user.getPassword());
            credential.setTemporary(false);

            userRepresentation.setCredentials(Collections.singletonList(credential));

            keycloak.realm(realm).users().create(userRepresentation);
        }
    }

    public AccessTokenResponse getAccessToken(String username, String password) {
        try (Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .username(username)
                .password(password)
                .build()) {
            return keycloak.tokenManager().getAccessToken();
        }
    }

    public void changePassword(Long userId, String newPassword) {
        try (Keycloak keycloak = keycloakAdminClient()) {
            // Необходимо найти пользователя по userId
            // Обновить пароль с помощью нового CredentialRepresentation
            // Это может потребовать вызова различных API Keycloak
        }
    }

    // Дополнительные методы для работы с Keycloak...
}
