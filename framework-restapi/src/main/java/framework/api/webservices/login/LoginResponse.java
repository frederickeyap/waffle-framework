package framework.api.webservices.login;

import framework.core.domain.session.Session;

public class LoginResponse {

    private final String sessionid;

    private final String username;

    protected LoginResponse(Session session) {
        this.sessionid = session.getId();
        this.username = session.getUser().getName();
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public String getUsername() {
        return username;
    }

}
