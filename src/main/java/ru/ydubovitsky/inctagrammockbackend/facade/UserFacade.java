package ru.ydubovitsky.inctagrammockbackend.facade;

import ru.ydubovitsky.inctagrammockbackend.dto.request.UserRegistrationRequestDto;
import ru.ydubovitsky.inctagrammockbackend.dto.response.UserResponseDto;
import ru.ydubovitsky.inctagrammockbackend.entity.User;

public class UserFacade {

    public static User userRegistrationDtoToUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        return User.builder()
                .username(userRegistrationRequestDto.getUsername())
                .password(userRegistrationRequestDto.getPassword())
                .password2(userRegistrationRequestDto.getPassword2())
                .email(userRegistrationRequestDto.getEmail())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
