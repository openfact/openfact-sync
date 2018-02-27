package org.clarksnut.models;

import java.util.List;

public interface SpaceProvider {

    /**
     * @param assignedId unique identity generated by the user
     * @param name       name of the space
     * @return created space
     */
    SpaceModel addSpace(UserModel user, String assignedId, String name);

    /**
     * Find space by id
     *
     * @param id identity auto generated by the system
     * @return space, is not found return null
     */
    SpaceModel getSpace(String id);

    /**
     * @param assignedId unique identity generated by the user
     * @return space, is not found return null
     */
    SpaceModel getByAssignedId(String assignedId);

    /**
     * @param space space to be removed
     * @return true if operation is success
     */
    boolean removeSpace(SpaceModel space);

    /**
     * @param user owner
     * @return spaces owned by user
     */
    List<SpaceModel> getSpaces(UserModel user);

    /**
     * @param user   owner
     * @param offset offset
     * @param limit  limit
     * @return spaces owned by user
     */
    List<SpaceModel> getSpaces(UserModel user, int offset, int limit, PermissionType... role);
    int countSpaces(UserModel user, PermissionType... role);

    List<SpaceModel> getSpaces(String filterText);
    List<SpaceModel> getSpaces(String filterText, int offset, int limit);
}
