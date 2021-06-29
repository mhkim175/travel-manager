package com.mhkim.tms.controller.v1.user;

import com.mhkim.tms.controller.v1.user.dto.UserDto;
import com.mhkim.tms.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"User"})
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private static final String ALL_USERS = "all-users";

    private static final String GET_USER = "get-user";

    private static final String UPDATE_USER = "update-user";

    private static final String DELETE_USER = "delete-user";

    private final UserService userService;

    @ApiOperation(value = "사용자 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<UserDto.Response>> getUsers() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        userService.getUsers().stream()
                                .map(user -> new UserDto.Response(user)
                                        .add(linkTo(methodOn(UserController.class).getUser(user.getUserIdx())).withSelfRel())
                                        .add(linkTo(UserController.class).slash(user.getUserIdx()).withRel(UPDATE_USER))
                                        .add(linkTo(methodOn(UserController.class).deleteUser(user.getUserIdx())).withRel(DELETE_USER))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(UserController.class).getUsers()).withSelfRel())
        );
    }

    @ApiOperation(value = "사용자 개별 조회")
    @GetMapping(value = "/{userIdx}")
    public ResponseEntity<UserDto.Response> getUser(@PathVariable("userIdx") Long userIdx) {
        var user = userService.getUser(userIdx);
        return ResponseEntity.ok(
                new UserDto.Response(user)
                        .add(linkTo(methodOn(UserController.class).getUser(userIdx)).withSelfRel())
                        .add(linkTo(UserController.class).slash(userIdx).withRel(UPDATE_USER))
                        .add(linkTo(methodOn(UserController.class).deleteUser(userIdx)).withRel(DELETE_USER))
        );
    }

    @ApiOperation(value = "사용자 추가")
    @PostMapping
    public ResponseEntity<UserDto.Response> addUser(@RequestBody UserDto.Request param) {
        var user = userService.addUser(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(UserController.class).getUser(user.getUserIdx())).withSelfRel().toUri()
        ).body(new UserDto.Response(user).add(linkTo(methodOn(UserController.class).getUsers()).withRel(ALL_USERS)));
    }

    @ApiOperation(value = "사용자 수정")
    @PatchMapping(value = "/{userIdx}")
    public ResponseEntity<UserDto.Response> updateUser(@PathVariable Long userIdx, @RequestBody UserDto.Update param) {
        var user = userService.updateUser(userIdx, param.getName());
        return ResponseEntity.ok(
                new UserDto.Response(user)
                        .add(linkTo(methodOn(UserController.class).updateUser(userIdx, param)).withSelfRel())
                        .add(linkTo(methodOn(UserController.class).getUsers()).withRel(ALL_USERS))
                        .add(linkTo(methodOn(UserController.class).getUser(userIdx)).withRel(GET_USER))
                        .add(linkTo(methodOn(UserController.class).deleteUser(userIdx)).withRel(DELETE_USER))
        );
    }

    @ApiOperation(value = "사용자 삭제")
    @DeleteMapping(value = "/{userIdx}")
    public ResponseEntity<UserDto.Response> deleteUser(@PathVariable Long userIdx) {
        var user = userService.deleteUser(userIdx);
        return ResponseEntity.ok(
                new UserDto.Response(user)
                        .add(linkTo(methodOn(UserController.class).getUsers()).withSelfRel())
        );
    }

}
