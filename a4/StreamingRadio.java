package cs445.a4;

/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * ratings that users assign to songs.
 */
public interface StreamingRadio {

    /**
     * The abstract methods below are declared as void methods with no
     * parameters. You need to expand each declaration to specify a return type
     * and parameters, as necessary. You also need to include a detailed comment
     * for each abstract method describing its effect, its return value, any
     * corner cases that the client may need to consider, any exceptions the
     * method may throw (including a description of the circumstances under
     * which this will happen), and so on. You should include enough details
     * that a client could use this data structure without ever being surprised
     * or not knowing what will happen, even though they haven't read the
     * implementation.
     */

    /**
     * Adds a new song to the system.
     * <p>If theSong is not null, and the system does not contain theSong, and the system's capacity
     * is not full, then this method will modify the system and add theSong to the system, and return true.
     *
     * <p> If theSong is null, then addSong throws a NullPointerException without modifying the system.
     * If the system already contains theSong, then this method will return false without modifying the system.
     * If the system's capacity is full, then return false without modifying the system.
     * <p>
     * If theSong is invalid, then this method will throw an IllegalArgumentException without modifying the system.
     * <p>
     * If the system is in an inappropriate state, then this method will throw an IllegalStateException.
     *
     * @param theSong the song to be added into the system.
     * @return true if the addition is successful,
     * false otherwise due to non-exceptional situations.
     * @throws NullPointerException     if theSong is null
     * @throws IllegalArgumentException if theSong is invalid.
     * @throws IllegalStateException    if the system is in an inappropriate state.
     */
    public boolean addSong(Song theSong)
            throws NullPointerException, IllegalArgumentException, IllegalStateException;


    /**
     * Removes an existing song from the system.
     *
     * <p>If the system contains theSong, then this method will modify the system and remove theSong from the system,
     * and return true.
     *
     * <p>If the system does not contain theSong, then this method will return false without modifying the system.
     *
     * <p>If theSong is null, then this method throws a NullPointerException without modifying the system.
     * <p>
     * If theSong is invalid, then this method will throw an IllegalArgumentException without modifying the system.
     * <p>
     * If the system is in an inappropriate state, then this method will throw an IllegalStateException.
     *
     * @param theSong the song to be removed from the system.
     * @return true if removal is successful, false if removal is unsuccessful due to non-exceptional situations.
     * @throws NullPointerException     if theSong is null.
     * @throws IllegalArgumentException if theSong is invalid.
     * @throws IllegalStateException    if the system is in an inappropriate state.
     */
    public boolean removeSong(Song theSong)
            throws NullPointerException, IllegalArgumentException, IllegalStateException;


    /**
     * Adds an existing song to the playlist for an existing radio station.
     * <p>
     * If theSong is null, then this method will throw a NullPointerException without modifying theStation.
     * <p>
     * If theStation is null, then this method will throw a NullPointerException without modifying theStation.
     * <p>
     * If theSong does not exist in the system, or if theStation is at full capacity,
     * then this method will return false without modifying theStation.
     * <p>
     * If theStation does not contain theSong, and the station is not full, then theSong will be
     * added to theStation and this method will return true.
     * <p>
     * If the station already contains theSong, then this method will return false without modifying theStation.
     * <p>
     * If either theStation is invalid or theSong is invalid, then this method will throw an IllegalArgumentException
     * without modifying theStation.
     * <p>
     * If the system is in an inappropriate state, then this method will throw an IllegalStateException.
     *
     * @param theSong    the song to be added to the station.
     * @param theStation the station that the song will be added to.
     * @return true if the addition is successful.
     * false otherwise due to non-exceptional situations.
     * @throws NullPointerException     if either theSong is null, or theStation is null.
     * @throws IllegalArgumentException if either theStation is invalid, or theSong is invalid.
     * @throws IllegalStateException    if the system is in an inappropriate state.
     */
    public boolean addToStation(Song theSong, Station theStation)
            throws NullPointerException, IllegalArgumentException, IllegalStateException;


    /**
     * Removes a song from the playlist for a radio station.
     * <p>
     * If theSong is null, then removeFromStation will throw a NullPointerException without modifying theStation.
     * <p>
     * If theStation is null, then removeFromStation will throw a NullPointerException without modifying theStation.
     * <p>
     * If theStation does not contain theSong, then the removeFromStation will return false without modifying theStation.
     * <p>
     * If theStation contains theSong, then the removeFromStation will remove theSong from theStation and return true.
     * <p>
     * If either theStation is invalid or theSong is invalid, then this method will throw an IllegalArgumentException without
     * modifying theStation.
     * <p>
     * If the system is in an inappropriate state, then this method will throw an IllegalStateException.
     *
     * @param theSong    the song to be removed from the station.
     * @param theStation the station that the song will be removed from.
     * @return true if the removal is successful,
     * false otherwise due to non-exceptional situations.
     * @throws NullPointerException     if theSong is null or theStation is null.
     * @throws IllegalArgumentException if either theStation is invalid or theSong is invalid.
     * @throws IllegalStateException    if the system is in an inappropriate state.
     */

    public boolean removeFromStation(Song theSong, Station theStation)
            throws NullPointerException, IllegalArgumentException, IllegalStateException;

    /**
     * Sets a user's rating for a song, as a number of stars from 1 to 5.
     * <p>
     * If theUser has not rated theSong, then the rating will be added.
     * <p>
     * If theUser has already rated theSong, then the previous rating will be cleared
     * and the new rating will be added.
     * <p>
     * If the rating given is not an int from 1 to 5, then this method will throw an
     * IllegalArgumentException without modifying the rating.
     * <p>
     * If either theUser or theSong or the rating is null, then this method will throw an NullPointerException
     * without modifying the rating.
     * <p>
     * If either theUser is invalid or theSong is invalid, then this method will throw an IllegalArgumentException
     * without modifying the rating.
     *
     * @param theUser user whose rating should be added
     * @param theSong song from which the user's rating should be added
     * @param rating  int value from 1 to 5 which indicates the number of stars rated for theSong
     * @throws IllegalArgumentException if the rating is not an int from 1 to 5,
     *                                  or if theUser is invalid,
     *                                  or if theSong is invalid.
     * @throws NullPointerException     if theUser is null or theSong or the rating is null
     */
    public void rateSong(User theUser, Song theSong, int rating)
            throws IllegalArgumentException, NullPointerException;

    /**
     * Clears a user's rating on a song. If this user has rated this song and
     * the rating has not already been cleared, then the rating is cleared and
     * the state will appear as if the rating was never made. If there is no
     * such rating on record (either because this user has not rated this song,
     * or because the rating has already been cleared), then this method will
     * throw an IllegalArgumentException.
     *
     * @param theUser user whose rating should be cleared
     * @param theSong song from which the user's rating should be cleared
     * @throws IllegalArgumentException if the user does not currently have a
     *                                  rating on record for the song
     * @throws NullPointerException     if either the user or the song is null
     */
    public void clearRating(User theUser, Song theSong)
            throws IllegalArgumentException, NullPointerException;

    /**
     * Predicts the rating a user will assign to a song that they have not yet
     * rated, as a number of stars from 1 to 5.
     * <p>
     * If theSong has already been rated by the user, then this method will throw an IllegalArgumentException.
     * <p>
     * If theSong has not been rated by the user, then this method will return an int from 1 to 5 based on the
     * user's previous ratings of similar songs to theSong.
     * <p>
     * If either theUser or theSong is null, then this method will throw an NullPointerException.
     * <p>
     * If there is no song or not enough song that was previously rated by the user to make a prediction, then this method will
     * throw an IllegalStateException.
     * <p>
     * If either theUser is invalid or theSong is invalid , then this method will throw an IllegalArgumentException.
     *
     * @param theUser the user whose song rating will be predicted.
     * @param theSong the song that's rating will be predicted.
     * @return a predicted song rating which is a int from 1 to 5.
     * @throws IllegalArgumentException if the song has already been predicted by the user,
     *                                  or if theUser is invalid,
     *                                  or if theSong is invalid.
     * @throws NullPointerException     if either the user or the song is null.
     * @throws IllegalStateException    if there is no song or not enough song that was previously rated by the user to make a prediction
     */
    public int predictRating(User theUser, Song theSong)
            throws IllegalArgumentException, NullPointerException, IllegalStateException;
    /**
     * Suggests a song for a user that they are predicted to like.
     * <p>
     * This method will suggest and return a song that the user has not yet rated based on the user's
     * previous ratings of song.
     * <p>
     * If the user has not rated any songs or if there are not enough ratings to suggest a song,
     * then this method will return a random song.
     * <p>
     * If the user is null, then this method will throw an NullPointerException.
     * <p>
     * If there is no song in the system to make a suggestion, then this method will
     * throw an IllegalStateException
     * <p>
     * If theUser is invalid, then this method will throw an IllegalArgumentException.
     *
     * @param theUser the user who will be suggested songs.
     * @return a song that the user is predicted to like.
     * @throws NullPointerException     if the user is null.
     * @throws IllegalStateException    if there is no song in the system to make a suggestion
     *                                  or if theUser is invalid.
     * @throws IllegalArgumentException if theUser is invalid.
     */
    public Song suggestSong(User theUser)
            throws NullPointerException, IllegalStateException, IllegalArgumentException;

}
