package isep.controller;

import java.util.List;
import isep.domain.model.SystemUser;
import isep.repository.IUserRepoJpa;
import isep.repository.UserRepoJpa;

public class RegisterUserController {
  IUserRepoJpa repo;

  public RegisterUserController() {
    this.repo = new UserRepoJpa();
  }

  public SystemUser addUser(String email, String fullName, String shortName, String password) {
    SystemUser user = new SystemUser(email, fullName, shortName, password);
    return repo.add(user);
  }

  public List<SystemUser> listUsers() {
    return repo.findAll();
  }

  public SystemUser findUser(Long id) {
    return repo.findById(id);
  }
}
