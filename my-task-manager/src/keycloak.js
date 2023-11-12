import Keycloak from 'keycloak-js';

const keycloakConfig = {
  url: 'http://localhost:8080/auth', // URL сервера Keycloak
  realm: 'MiniProjectRealm', // Замените на ваш realm
  clientId: 'react-app-client-12-11', // Замените на ваш clientId
};

const keycloak = new Keycloak(keycloakConfig);
export default keycloak;
