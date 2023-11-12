import React from 'react';
import CookieConsent from 'react-cookie-consent';

function CookieConsentComponent() {
  return (
    <CookieConsent
      location="bottom"
      buttonText="Принять"
      cookieName="userConsentCookie"
      style={{ background: "#2B373B" }}
      buttonStyle={{ color: "#4e503b", fontSize: "13px" }}
      expires={150}
    >
      Этот сайт использует куки для улучшения вашего опыта.
    </CookieConsent>
  );
}

export default CookieConsentComponent;
