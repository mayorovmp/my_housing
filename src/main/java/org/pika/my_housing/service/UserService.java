package org.pika.my_housing.service;

import org.pika.my_housing.entities.SessionEntity;
import org.pika.my_housing.entities.UserEntity;
import org.pika.my_housing.exceptions.ApiException;
import org.pika.my_housing.repos.SessionRepo;
import org.pika.my_housing.repos.UserRepo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final SessionRepo sessionRepo;
    private final UserRepo userRepo;

    public UserService(SessionRepo sessionRepo, UserRepo userRepo) {
        this.sessionRepo = sessionRepo;
        this.userRepo = userRepo;
    }

    public UserEntity findByLogin(String login) throws ApiException {
        return this.userRepo.findByLogin(login)
                .orElseThrow(()->new ApiException("Пользователь не найден"));
    }

    public Optional<User> findByToken(String token) {
        var optSession = sessionRepo.findByToken(token);
        if(optSession.isPresent()){
            var session = optSession.get();
            User user = new User(session.getUser().getLogin(), session.getUser().getPass(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

    public void register(String login, String pass) {
        var user = userRepo.findByLogin(login);
        if (user.isPresent())
            throw new ApiException("Пользователь уже существует");
        var userEntity = new UserEntity(login, pass);
        userRepo.saveAndFlush(userEntity);
    }

    public String login(String login, String pass) throws ApiException {
        var user = userRepo.findByLoginAndPass(login, pass);
        if (user.isEmpty())
            throw new ApiException("Пользователь не найден");
        UUID uuid = UUID.randomUUID();
        SessionEntity session = new SessionEntity(
                user.get(),
                new Timestamp(System.currentTimeMillis()),
                uuid.toString());
        sessionRepo.saveAndFlush(session);
        return session.getToken();
    }
}
