package mate.academy.service;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);

    @Override
    public User login(String login, String password) throws AuthenticationException {
        logger.info("Method login was called for user login='{}'", login);
        User user = findByLogin(login);
        if (!user.getPassword().equals(password)) {
            logger.warn("Failed login attempt for user login='{}'", login);
            throw new AuthenticationException("Username or password are incorrect");
        }
        logger.info("User '{}' successfully logged in", login);
        return user;
    }

    private User findByLogin(String login) {
        User user = new User(login, "1234");
        user.setUserId(2L);
        logger.debug("User fetched by login='{}' with dummy userId={}", login, user.getUserId());
        return user;
    }
}
