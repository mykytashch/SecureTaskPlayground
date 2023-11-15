package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.AccessTokenResponse;

import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    KeycloakService keycloakService;

    @POST
    @Path("/register")
    public Response registerUser(User user) {
        keycloakService.createUser(user);
        userRepository.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/login")
    public Response loginUser(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            AccessTokenResponse token = keycloakService.getAccessToken(username, password);
            if (token != null) {
                return Response.ok(token).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing login").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User updatedUser) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            userRepository.updateUser(user);
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userRepository.deleteUser(id);
        // Add logic to remove user from Keycloak as well
        return Response.noContent().build();
    }

    @POST
    @Path("/{id}/change-password")
    public Response changeUserPassword(@PathParam("id") Long id, @FormParam("newPassword") String newPassword) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.updateUser(user);
            keycloakService.changePassword(id, newPassword);
            return Response.ok().entity("Password updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
    }

    // Additional endpoints and methods...
}
