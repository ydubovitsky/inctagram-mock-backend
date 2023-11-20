package ru.ydubovitsky.inctagrammockbackend.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.inctagrammockbackend.dto.request.UserRegistrationRequestDto;
import ru.ydubovitsky.inctagrammockbackend.dto.request.UserUpdateRequestDto;
import ru.ydubovitsky.inctagrammockbackend.dto.response.UserResponseDto;
import ru.ydubovitsky.inctagrammockbackend.entity.User;
import ru.ydubovitsky.inctagrammockbackend.facade.UserFacade;
import ru.ydubovitsky.inctagrammockbackend.service.UserService;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        return UserFacade.userToUserResponseDto(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = userService.createNewUser(UserFacade.userRegistrationDtoToUser(userRegistrationRequestDto));
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(
                    String.format("User %s already exists", userRegistrationRequestDto.getUsername()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateUserData(
            @RequestBody UserUpdateRequestDto userUpdateRequestDto,
            Principal principal
    ) {
        User user = userService.updateUser(userUpdateRequestDto, principal);
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(UserFacade.userToUserResponseDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
