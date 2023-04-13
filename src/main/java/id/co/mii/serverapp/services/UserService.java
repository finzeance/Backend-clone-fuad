package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

  private RoleService roleService;
  private UserRepository userRepository;

  // private ModelMapper modelMapper;
  // private PasswordEncoder passwordEncoder;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Data User not found!!!"
        )
      );
  }

  // public User create(UserRequest userRequest) {
  //   Employee employee = modelMapper.map(userRequest, Employee.class);
  //   User user = modelMapper.map(userRequest, User.class);

  //   // set password
  //   user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

  //   // set Role
  //   List<Role> roles = new ArrayList<>();
  //   roles.add(roleService.getById(3));
  //   user.setRoles(roles);

  //   employee.setUser(user);
  //   user.setEmployee(employee);
  //   return userRepository.save(user);
  // }

  public User update(Integer id, User user) {
    getById(id); // method getById
    user.setId(id);
    return userRepository.save(user);
  }

  public User delete(Integer id) {
    User user = getById(id);
    userRepository.delete(user);
    return user;
  }

  public User addRole(Integer id, Role role) {
    User user = getById(id);
    List<Role> roles = user.getRoles();
    roles.add(roleService.getById(role.getId()));
    user.setRoles(roles);
    return userRepository.save(user);
  }
}
// Task teman2 buat setRole menjadi user secara otomatis pada saat create user
